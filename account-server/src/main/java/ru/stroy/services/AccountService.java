package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import ru.stroy.dto.enumeration.PermissionAccountEnum;
import ru.stroy.dto.request.AccountUpdateDto;
import ru.stroy.entity.datasource.Account;
import ru.stroy.feign.IdpClientFeignClient;
import ru.stroy.repositories.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final IdpClientFeignClient idpClientFeignClient;

    public Mono<Account> createEmptyAccount() {
        Account account = new Account();
        return accountRepository.save(account);
    }

    public Mono<Account> getContextAccount() {
        return Mono.just(idpClientFeignClient.getContextAccount())
                .map(Account::getId)
                .flatMap(accountRepository::findById);
    }

    public Account updateAccountByDto(AccountUpdateDto accountUpdateDto) {
        Account account = getContextAccount().block();
        account.setName(accountUpdateDto.getName());
        account.setBirth(accountUpdateDto.getBirth());
        account.setAvatarUrl(accountUpdateDto.getAvatarUrl());
        account.setEmail(accountUpdateDto.getEmail());
        return accountRepository.save(account).block();
    }

    public Mono<Account> getAccountById(Long id) {
        return accountRepository.findById(id);
    }

    public void approveAccount(Long id) {
        Account account = accountRepository.findById(id)
                .blockOptional()
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.setPermission(PermissionAccountEnum.Moderator.getCode());
        accountRepository.save(account);
    }

    public void fireAccount(Long id) {
        Account account = accountRepository.findById(id)
                .blockOptional()
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));
        account.setPermission(PermissionAccountEnum.User.getCode());
        accountRepository.save(account);
    }
}
