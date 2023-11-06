package ru.stroy.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stroy.dto.request.DocumentPutDto;
import ru.stroy.entity.datasource.Document;
import ru.stroy.services.DocumentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/document")
@Validated
public class DocumentController {
    private final DocumentService documentService;

    @PutMapping
    public void attachDocumentToRespond(@Valid @RequestBody DocumentPutDto documentPutDto) {
        documentService.putDocumentToRespondByDto(documentPutDto);
    }

    @GetMapping
    @ResponseBody
    public List<Document> getUserDocuments() {
        return documentService.getDocumentsOfCurrentUser();
    }
}
