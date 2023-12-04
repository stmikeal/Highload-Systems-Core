package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.stroy.entity.datasource.*;
import ru.stroy.feign.AccountClientFeignClient;
import ru.stroy.repositories.AccountRepository;
import ru.stroy.repositories.security.LoginRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AccountService {
    private final LoginRepository loginRepository;
    private final AccountClientFeignClient accountClientFeignClient;
    private final AccountRepository accountRepository;

    public Account getContextAccount() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Login> login = loginRepository.findByUsername(name);
        return login.map(Login::getAccount).orElse(new Account());
    }

    public Account createEmptyAccount () {
        Long id = accountClientFeignClient.createEmptyAccount();
        return accountRepository.findById(id).get();
    }

}
