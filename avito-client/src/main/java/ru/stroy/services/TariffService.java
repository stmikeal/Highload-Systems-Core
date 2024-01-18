package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroy.dto.request.AttachCompanyTariffDto;
import ru.stroy.entity.datasource.Company;
import ru.stroy.entity.datasource.Tariff;
import ru.stroy.repositories.CurrencyTypeRepository;
import ru.stroy.repositories.TariffRepository;

@Service
@RequiredArgsConstructor
public class TariffService {
    private final TariffRepository tariffRepository;
    private final CurrencyTypeRepository currencyTypeRepository;

    public void createTariffByDto(Company company, AttachCompanyTariffDto attach) {
        Tariff tariff = new Tariff();
        tariff.setCompany(company);
        tariff.setCurrency(currencyTypeRepository.findByCode(attach.getCurrency().getCode()));
        tariff.setLimit(attach.getWeight());
        tariff.setPrice(attach.getPrice());
        tariffRepository.save(tariff);
    }
}
