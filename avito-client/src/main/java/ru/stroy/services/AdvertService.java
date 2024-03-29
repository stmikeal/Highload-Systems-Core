package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroy.dto.request.AdvertCreateDto;
import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.Advert;
import ru.stroy.exceptions.AccessDeniedException;
import ru.stroy.repositories.AdvertRepository;
import ru.stroy.repositories.AdvertTypeRepository;
import ru.stroy.repositories.CurrencyTypeRepository;

@Service
@RequiredArgsConstructor
public class AdvertService {
    private final CompanyService companyService;
    private final CurrencyTypeRepository currencyTypeRepository;
    private final AdvertTypeRepository advertTypeRepository;
    private final AdvertRepository advertRepository;
    private final AccountService accountService;

    public void createAdvertByDto(AdvertCreateDto advertCreateDto) {
        Advert advert = new Advert();
        if (advertCreateDto.getCompanyId() != null)
            advert.setCompany(companyService.getCompanyWithPermission(advertCreateDto.getCompanyId()));
        advert.setAuthor(accountService.getContextAccount());
        advert.setCurrency(currencyTypeRepository.findByCode(advertCreateDto.getCurrency().getCode()));
        advert.setDescription(advertCreateDto.getDescription());
        advert.setPrice(advertCreateDto.getPrice());
        advert.setTitle(advertCreateDto.getTitle());
        advert.setType(advertTypeRepository.findByCode(advertCreateDto.getAdvertType().getCode()));
        advertRepository.save(advert);
    }

    public void deleteAdvert(Long advertId) {
        Advert advert = advertRepository
                .findById(advertId).orElseThrow(() -> new IllegalArgumentException("Not found advert"));
        Account account = accountService.getContextAccount();
        if (!advert.getAuthor().getId().equals(account.getId())) {
            throw new AccessDeniedException("The user is not owner of advert");
        }
        advertRepository.delete(advert);
    }
}
