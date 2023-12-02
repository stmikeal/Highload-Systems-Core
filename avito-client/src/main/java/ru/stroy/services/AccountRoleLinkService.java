package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.AccountRole;
import ru.stroy.entity.datasource.AccountRoleConfirmation;
import ru.stroy.entity.datasource.AccountRoleLink;
import ru.stroy.repositories.AccountRoleLinkRepository;

@Service
@RequiredArgsConstructor
public class AccountRoleLinkService {
    private final AccountRoleLinkRepository accountRoleLinkRepository;

    public AccountRoleLink createRoleLink(Account account, AccountRoleConfirmation confirm, AccountRole role) {
        AccountRoleLink accountRoleLink = new AccountRoleLink();
        accountRoleLink.setAccount(account);
        accountRoleLink.setConfirm(confirm);
        accountRoleLink.setRole(role);
        return accountRoleLinkRepository.save(accountRoleLink);
    }
}
