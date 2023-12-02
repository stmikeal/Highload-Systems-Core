package ru.stroy.services;

import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.stroy.dto.request.AdvertRespondCreateDto;
import ru.stroy.entity.datasource.Advert;
import ru.stroy.entity.datasource.AdvertRespond;
import ru.stroy.entity.datasource.Document;
import ru.stroy.repositories.AdvertRepository;
import ru.stroy.repositories.AdvertRespondRepository;

import javax.management.openmbean.KeyAlreadyExistsException;

@Service
@RequiredArgsConstructor
public class RespondService {
    private final AdvertRespondRepository advertRespondRepository;
    private final AccountService accountService;
    private final AdvertRepository advertRepository;
    private final DocumentService documentService;

    public AdvertRespond createAdvertRespondByDto(AdvertRespondCreateDto advertRespondCreateDto) {

        Advert advert = advertRepository
                .findById(advertRespondCreateDto.getAdvertId())
                .orElseThrow(() -> new IllegalArgumentException("Not found advert"));
        if (advertRespondRepository.findByApplicantAndAdvert(accountService.getContextAccount(), advert).isPresent()) {
            throw new KeyAlreadyExistsException("Respond already exists");
        }

        AdvertRespond advertRespond = new AdvertRespond();
        advertRespond.setApplicant(accountService.getContextAccount());
        advertRespond.setAdvert(advert);
        advertRespond.setTitle(advertRespondCreateDto.getTitle());
        advertRespond.setText(advertRespondCreateDto.getDescription());
        return advertRespondRepository.save(advertRespond);
    }

    public AdvertRespond setAdvertRespondByDto(AdvertRespondCreateDto advertRespondCreateDto, Long advertId) {
        AdvertRespond advertRespond = getRespondByAdvert(advertId);
        advertRespond.setTitle(advertRespondCreateDto.getTitle());
        if(advertRespondCreateDto.getDescription() != null) {
            advertRespond.setText(advertRespondCreateDto.getDescription());
        }
        return advertRespondRepository.save(advertRespond);
    }

    public void deleteAdvertRespondByDto(Long advertId){
        AdvertRespond advertRespond = getRespondByAdvert(advertId);
        advertRespondRepository.delete(advertRespond);
    }

    public void attachDocumentRespond(Long advertId, Long documentId) {
        Document document = documentService.getDocumentWithPermission(documentId);
        AdvertRespond advertRespond = getRespondByAdvert(advertId);
        advertRespond.getDocuments().add(document);
        advertRespondRepository.save(advertRespond);
    }

    private AdvertRespond getRespondByAdvert(Long advertId) {
        Advert advert = advertRepository
                .findById(advertId).orElseThrow(() -> new IllegalArgumentException("Not found advert"));
        return advertRespondRepository
                .findByApplicantAndAdvert(accountService.getContextAccount(), advert)
                .orElseThrow(() -> new IllegalArgumentException("Not found respond"));
    }
}
