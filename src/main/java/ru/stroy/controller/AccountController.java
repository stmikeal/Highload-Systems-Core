package ru.stroy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import ru.stroy.dto.request.AccountUpdateDto;
import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.Login;
import ru.stroy.repositories.AccountRepository;
import ru.stroy.repositories.security.LoginRepository;

import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {
    private final LoginRepository loginRepository;
    private final AccountRepository accountRepository;

    @GetMapping
    @ResponseBody
    public Account getAccountInfo() {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Login> login = loginRepository.findByUsername(name);
        return login.map(Login::getAccount).orElse(new Account());
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Account getAccountInfo(@PathVariable Long id) {
        Optional<Login> login = loginRepository.findById(id);
        return login.map(Login::getAccount).orElse(new Account());
    }

    @PostMapping
    @ResponseBody
    public void getAccountInfo(@RequestBody AccountUpdateDto accountUpdateDto) throws Exception {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Optional<Login> login = loginRepository.findByUsername(name);
        Account account =
                login.map(Login::getAccount).orElseThrow(() -> new UserPrincipalNotFoundException("Can't find user"));
        account.setName(accountUpdateDto.getName());
        account.setBirth(accountUpdateDto.getBirth());
        account.setAvatarUrl(accountUpdateDto.getAvatarUrl());
        accountRepository.save(account);
    }
}
