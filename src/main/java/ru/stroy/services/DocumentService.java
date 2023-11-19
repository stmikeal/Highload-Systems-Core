package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.stroy.dto.request.DocumentPutDto;
import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.Document;
import ru.stroy.repositories.DocumentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final ContextAccountService contextAccountService;

    public Document putDocumentByDto(DocumentPutDto documentPutDto) {
        Document document = new Document();
        document.setUrl(documentPutDto.getUrl());
        document.setDescription(documentPutDto.getDescription());
        document.setTitle(documentPutDto.getTitle());
        document.setAuthor(contextAccountService.getContextAccount());
        documentRepository.save(document);
        return document;
    }

    public List<Document> getDocumentsOfCurrentUser() {
        return documentRepository.findAllByAuthor(contextAccountService.getContextAccount());
    }

    public Document getDocumentWithPermission(Long documentId) {
        Document document = documentRepository
                .findById(documentId).orElseThrow(() -> new IllegalArgumentException("Not found such document"));
        Account account = contextAccountService.getContextAccount();
        if (!document.getAuthor().getId().equals(account.getId())) {
            throw new AccessDeniedException("Cannot access this document");
        }
        return document;
    }
}
