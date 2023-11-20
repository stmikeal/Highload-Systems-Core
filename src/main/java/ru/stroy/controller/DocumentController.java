package ru.stroy.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stroy.dto.request.DocumentPutDto;
import ru.stroy.entity.datasource.Document;
import ru.stroy.repositories.DocumentRepository;
import ru.stroy.services.DocumentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/document")
@Validated
public class DocumentController {
    private final DocumentService documentService;
    private final DocumentRepository documentRepository;

    @PutMapping
    public void attachDocumentToRespond(@Valid @RequestBody DocumentPutDto documentPutDto) {
        documentService.putDocumentByDto(documentPutDto);
    }

    @GetMapping
    public ResponseEntity<Page<Document>> getAllAdvert(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(50) Integer limit
    ) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_RANGE, String.valueOf(documentRepository.count()));
        return ResponseEntity.ok()
                .headers(headers)
                .body(documentRepository.findAll(PageRequest.of(offset, limit)));
    }
}
