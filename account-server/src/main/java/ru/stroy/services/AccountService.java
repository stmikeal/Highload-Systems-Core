package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.stroy.dto.enumeration.PermissionAccountEnum;
import ru.stroy.dto.request.AccountPutConfirmationDto;
import ru.stroy.dto.request.AccountUpdateDto;
import ru.stroy.entity.datasource.*;
import ru.stroy.feign.IdpClientFeignClient;
import ru.stroy.repositories.AccountRepository;
import ru.stroy.repositories.security.LoginRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final LoginRepository loginRepository;
    private final IdpClientFeignClient idpClientFeignClient;

    public Account createEmptyAccount() {
        Account account = new Account();
        return accountRepository.save(account);
    }

    public Account getContextAccount() {
        Long id = idpClientFeignClient.getContextAccount().getId();
        return accountRepository.findById(id).get();
    }

    public Account updateAccountByDto(AccountUpdateDto accountUpdateDto) {
        Account account = getContextAccount();
        account.setName(accountUpdateDto.getName());
        account.setBirth(accountUpdateDto.getBirth());
        account.setAvatarUrl(accountUpdateDto.getAvatarUrl());
        return accountRepository.save(account);
    }

    public Account getAccountById(Long id) {
        return accountRepository.findById(id).orElse(new Account());
    }

    public void approveAccount(Long id) {
        Account account = accountRepository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.setPermission(PermissionAccountEnum.Moderator.getCode());
        accountRepository.save(account);
    }

    public void fireAccount(Long id) {
        Account account = accountRepository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.setPermission(PermissionAccountEnum.User.getCode());
        accountRepository.save(account);
    }
}
