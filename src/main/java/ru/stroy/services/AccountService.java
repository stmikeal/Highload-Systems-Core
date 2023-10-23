package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.stroy.entity.datasource.Account;
import ru.stroy.repositories.AccountRepository;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public Account createEmptyAccount() {
        Account account = new Account();
        return accountRepository.save(account);
    }

}
