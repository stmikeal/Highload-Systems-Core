package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.Login;
import ru.stroy.repositories.security.LoginRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ContextAccountService {
    private final LoginRepository loginRepository;

    public Account getContextAccount() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Login> login = loginRepository.findByUsername(name);
        return login.map(Login::getAccount).orElse(new Account());
    }
}
