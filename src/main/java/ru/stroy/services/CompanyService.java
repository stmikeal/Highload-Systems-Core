package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroy.dto.enumeration.AccessCompanyEnum;
import ru.stroy.dto.request.CompanyCreateDto;
import ru.stroy.entity.datasource.AccountCompanyLink;
import ru.stroy.entity.datasource.Company;
import ru.stroy.repositories.CompanyRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyService {
    private final AccountCompanyLinkService accountCompanyLinkService;
    private final CompanyRepository companyRepository;
    private final AccountService accountService;

    public Company createCompanyByCurrentUser(CompanyCreateDto companyCreateDto) {
        Company company = new Company();
        company.setName(companyCreateDto.getName());
        company.setAvatarUrl(companyCreateDto.getAvatarUrl());
        company = companyRepository.save(company);
        accountCompanyLinkService
                .createLink(accountService.getContextAccount(), AccessCompanyEnum.Owner, company, "Владелец");
        return company;
    }


}
