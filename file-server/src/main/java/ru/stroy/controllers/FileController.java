package ru.stroy.controllers;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.stroy.entity.datasource.FileAttachment;
import ru.stroy.exceptions.FileUploadException;
import ru.stroy.repositories.FileRepository;
import ru.stroy.services.FileService;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final FileRepository fileRepository;

    @PostMapping(path = "/upload")
    public @ResponseBody Long fileUpload(@RequestParam("file") MultipartFile file)
            throws FileUploadException, IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        String name = file.getOriginalFilename();
        if (file.isEmpty()) throw new FileUploadException("Файл не должен быть пустой");
        return fileService.saveFile(file.getBytes(), name, username);
    }

    @GetMapping
    public ResponseEntity<Page<FileAttachment>> getAllAdvert(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(50) Integer limit
    ) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        HttpHeaders headers = new HttpHeaders();
        return ResponseEntity.ok()
                .headers(headers)
                .body(fileRepository.findAllWithAuthor(PageRequest.of(offset, limit), username));
    }

    @GetMapping(path = "/download/{id}")
    public ResponseEntity<InputStreamResource> getFile(@PathVariable("id") Long id) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        byte[] bytes = fileService.loadFile(id, username);
        String filename = fileService.getFilename(id, username);
        InputStreamResource resource = new InputStreamResource(new ByteArrayInputStream(bytes));
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + filename)
                .contentLength(bytes.length)
                .body(resource);
    }

}
