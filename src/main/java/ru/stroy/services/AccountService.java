package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.stroy.dto.request.AccountPutConfirmationDto;
import ru.stroy.dto.request.AccountUpdateDto;
import ru.stroy.entity.datasource.*;
import ru.stroy.repositories.AccountRepository;
import ru.stroy.repositories.AccountRoleRepository;
import ru.stroy.repositories.security.LoginRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final LoginRepository loginRepository;
    private final AccountRoleConfirmService accountRoleConfirmService;
    private final DocumentService documentService;
    private final AccountRoleLinkService accountRoleLinkService;
    private final AccountRoleRepository accountRoleRepository;

    public Account createEmptyAccount() {
        Account account = new Account();
        return accountRepository.save(account);
    }

    public Account updateAccountByDto(AccountUpdateDto accountUpdateDto) {
        Account account = getContextAccount();
        account.setName(accountUpdateDto.getName());
        account.setBirth(accountUpdateDto.getBirth());
        account.setAvatarUrl(accountUpdateDto.getAvatarUrl());
        return accountRepository.save(account);
    }

    public Account getContextAccount() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Login> login = loginRepository.findByUsername(name);
        return login.map(Login::getAccount).orElse(new Account());
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(new Account());
    }

    public void confirmAccountByDto(AccountPutConfirmationDto accountDto) {
        Document document = documentService.getDocumentWithPermission(accountDto.getDocumentId());
        AccountRoleConfirmation roleConfirmation = accountRoleConfirmService
                .createAttachedDocument(document, accountDto.getSignature(), accountDto.getDescription());
        AccountRole accountRole = accountRoleRepository.findByCode(accountDto.getRole().getCode());
        accountRoleLinkService.createRoleLink(getContextAccount(), roleConfirmation, accountRole);
    }
}
