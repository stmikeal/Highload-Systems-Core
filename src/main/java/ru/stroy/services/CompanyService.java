package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Service;
import ru.stroy.dto.enumeration.AccessCompanyEnum;
import ru.stroy.dto.request.AttachCompanyAccountDto;
import ru.stroy.dto.request.AttachCompanyAreaDto;
import ru.stroy.dto.request.CompanyCreateDto;
import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.Area;
import ru.stroy.entity.datasource.Company;
import ru.stroy.repositories.AreaRepository;
import ru.stroy.repositories.CompanyRepository;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CompanyService {
    private final AccountCompanyLinkService accountCompanyLinkService;
    private final CompanyRepository companyRepository;
    private final AccountService accountService;
    private final AreaRepository areaRepository;

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
        Company company = getCompanyWithPermission(companyId);
        Account account = accountService.getAccountById(attach.getAccountId());
        accountCompanyLinkService
                .createLink(account, attach.getAccess(), company, attach.getPosition());
    }

    public void attachAreaToCompany(Long companyId, AttachCompanyAreaDto attach) {
        Company company = getCompanyWithPermission(companyId);
        List<Area> areas = company.getAreas();
        areas.add(areaRepository.findByCode(attach.getArea().getCode()));
        company.setAreas(areas);
        companyRepository.save(company);
    }

    private Company getCompanyWithPermission(Long companyId) {
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new IllegalArgumentException("Cannot find company"));
        Account account = accountService.getContextAccount();
        if (!accountCompanyLinkService.hasEditPermission(companyId, account.getId()))
            throw new AccessDeniedException("Cannot access this company");
        return company;
    }

}
