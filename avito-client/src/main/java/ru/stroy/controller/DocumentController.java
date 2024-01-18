package ru.stroy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import ru.stroy.dto.request.AccountPutConfirmationDto;
import ru.stroy.dto.request.DocumentPutDto;
import ru.stroy.entity.datasource.Document;
import ru.stroy.repositories.DocumentRepository;
import ru.stroy.services.AccountService;
import ru.stroy.services.DocumentService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/document")
@Validated
@Tag(name = "Document", description = "Module for working with documents")
public class DocumentController {
    private final DocumentService documentService;
    private final DocumentRepository documentRepository;
    private final AccountService accountService;

    @Operation(summary = "Attach document to respond")
    @PutMapping
    public void attachDocumentToRespond(@Valid @RequestBody DocumentPutDto documentPutDto) {
        documentService.putDocumentByDto(documentPutDto);
    }

    @Operation(summary = "Get list of documents")
    @GetMapping
    public ResponseEntity<Page<Document>> getDocument(@RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset, @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(50) Integer limit) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_RANGE, String.valueOf(documentRepository.count()));
        return ResponseEntity.ok().headers(headers).body(documentRepository.findAll(PageRequest.of(offset, limit)));
    }

    @Operation(summary = "Attach document to confirm account")
    @PostMapping("/confirm")
    public void setConfirmation(@Valid @RequestBody AccountPutConfirmationDto accountDto) {
        accountService.confirmAccountByDto(accountDto);
    }
}
