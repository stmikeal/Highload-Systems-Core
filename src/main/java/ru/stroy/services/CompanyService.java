package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroy.dto.enumeration.AccessCompanyEnum;
import ru.stroy.dto.request.AttachCompanyAccountDto;
import ru.stroy.dto.request.CompanyCreateDto;
import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.Company;
import ru.stroy.repositories.CompanyRepository;


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

    public void attachEmployeeToCompany(Long companyId, AttachCompanyAccountDto attach) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find company"));
        Account account = accountService.getAccountById(attach.getAccountId());
        accountCompanyLinkService
                .createLink(account, attach.getAccess(), company, attach.getPosition());
    }
}
