package ru.stroy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.stroy.dto.request.DocumentPutDto;
import ru.stroy.entity.datasource.Document;
import ru.stroy.services.DocumentService;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/document")
public class DocumentController {
    private final DocumentService documentService;

    @PutMapping
    public void attachDocumentToRespond(@RequestBody DocumentPutDto documentPutDto) {
        documentService.putDocumentToRespondByDto(documentPutDto);
    }

    @GetMapping
    @ResponseBody
    public List<Document> getUserDocuments() {
        return documentService.getDocumentsOfCurrentUser();
    }
}
