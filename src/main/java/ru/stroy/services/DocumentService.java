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

    public void putDocumentToRespondByDto(DocumentPutDto documentPutDto) throws ResourceAccessException {
        if (!advertRespondService.isOwnedRespond(documentPutDto.getRespondId()))
            throw new ResourceAccessException("User isn't applicant of respond");
        Document document = new Document();
        document.setUrl(documentPutDto.getUrl());
        document.setDescription(documentPutDto.getDescription());
        document.setTitle(documentPutDto.getTitle());
        documentRepository.attachToRespond(documentRepository.save(document).getId(), documentPutDto.getRespondId());
    }

    public List<Document> getDocumentsOfCurrentUser() {
        Account account = accountService.getContextAccount();
        return documentRepository.getApplicantDocuments(account.getId());
    }
}