package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroy.dto.request.AdvertRespondCreateDto;
import ru.stroy.entity.datasource.Advert;
import ru.stroy.entity.datasource.AdvertRespond;
import ru.stroy.repositories.AdvertRepository;
import ru.stroy.repositories.AdvertRespondRepository;

@Service
@RequiredArgsConstructor
public class RespondService {
    private final AdvertRespondRepository advertRespondRepository;
    private final AccountService accountService;
    private final AdvertRepository advertRepository;

    public AdvertRespond createAdvertRespondByDto(AdvertRespondCreateDto advertRespondCreateDto) {
        AdvertRespond advertRespond = new AdvertRespond();

        advertRespond.setApplicant(accountService.getContextAccount());
        advertRespond.setAdvert(
                advertRepository.findById(
                        advertRespondCreateDto.getAdvertId())
                            .orElseThrow(() -> new IllegalArgumentException("No such advert")
                )
        );
        advertRespond.setTitle(advertRespondCreateDto.getTitle());
        advertRespond.setText(advertRespondCreateDto.getDescription());
        return advertRespondRepository.save(advertRespond);
    }

    public AdvertRespond setAdvertRespondByDto(AdvertRespondCreateDto advertRespondCreateDto, Long advertId) {
        advertRepository.findById(advertId).orElseThrow(() -> new IllegalArgumentException("Not found advert"));
        AdvertRespond advertRespond = advertRespondRepository.getReferenceById(advertId);
        advertRespond.setTitle(advertRespondCreateDto.getTitle());
        if(advertRespondCreateDto.getDescription() != null) {
            advertRespond.setText(advertRespondCreateDto.getDescription());
        }
        return advertRespondRepository.save(advertRespond);
    }

    public void deleteAdvertRespondByDto(Long advertId){
        Advert advert = advertRepository
                .findById(advertId).orElseThrow(() -> new IllegalArgumentException("Not found advert"));
        AdvertRespond advertRespond = advertRespondRepository
                .findByApplicantAndAdvert(accountService.getContextAccount(), advert)
                .orElseThrow(() -> new IllegalArgumentException("Not found respond"));
        advertRespondRepository.delete(advertRespond);
    }
}
