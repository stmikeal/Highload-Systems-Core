package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroy.dto.enumeration.AccessCompanyEnum;
import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.AccountCompanyLink;
import ru.stroy.entity.datasource.Company;
import ru.stroy.repositories.AccessRepository;
import ru.stroy.repositories.AccountCompanyLinkRepository;

@Service
@RequiredArgsConstructor
public class AccountCompanyLinkService {
    private final AccountCompanyLinkRepository accountCompanyLinkRepository;
    private final AccessRepository accessRepository;

    public AccountCompanyLink createLink(Account account, AccessCompanyEnum access, Company company, String position) {
        AccountCompanyLink accountCompanyLink = new AccountCompanyLink();
        accountCompanyLink.setAccount(account);
        accountCompanyLink.setAccess(accessRepository.findByCode(access.getCode()));
        accountCompanyLink.setPosition(position);
        accountCompanyLink.setCompany(company);
        return accountCompanyLinkRepository.save(accountCompanyLink);
    }
}
