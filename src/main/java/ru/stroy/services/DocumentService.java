package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import ru.stroy.dto.request.DocumentPutDto;
import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.Document;
import ru.stroy.repositories.DocumentRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {
    private final DocumentRepository documentRepository;
    private final AdvertRespondService advertRespondService;
    private final AccountService accountService;

    public Document putDocumentByDto(DocumentPutDto documentPutDto) {
        Document document = new Document();
        document.setUrl(documentPutDto.getUrl());
        document.setDescription(documentPutDto.getDescription());
        document.setTitle(documentPutDto.getTitle());
        document.setAuthor(accountService.getContextAccount());
        documentRepository.save(document);
        return document;
    }

    public List<Document> getDocumentsOfCurrentUser() {
        return documentRepository.findAllByAuthor(accountService.getContextAccount());
    }
}
