package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.stroy.dto.request.DocumentPutDto;
import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.Document;

import ru.stroy.feign.IdpClientFeignClient;
import ru.stroy.repositories.AccountRepository;
import ru.stroy.repositories.DocumentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final IdpClientFeignClient idpClientFeignClient;
    private final AccountRepository accountRepository;

    public Account getContextAccount() {
        Long id = idpClientFeignClient.getContextAccount().getId();
        return accountRepository.getById(id);
    }

    public Document putDocumentByDto(DocumentPutDto documentPutDto) {
        Document document = new Document();
        document.setUrl(documentPutDto.getUrl());
        document.setDescription(documentPutDto.getDescription());
        document.setTitle(documentPutDto.getTitle());
        document.setAuthor(getContextAccount());
        documentRepository.save(document);
        return document;
    }

    public List<Document> getDocumentsOfCurrentUser() {
        return documentRepository.findAllByAuthor(getContextAccount());
    }

    public Document getDocumentWithPermission(Long documentId) {
        Document document = documentRepository
                .findById(documentId)
                .orElseThrow(() -> new IllegalArgumentException("Not found such document"));
        Account account = getContextAccount();
        if (!document.getAuthor().getId().equals(account.getId())) {
            throw new AccessDeniedException("Cannot access this document");
        }
        return document;
    }
}
