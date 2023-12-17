package ru.stroy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.stroy.entity.datasource.Account;
import ru.stroy.services.AccountService;

@RestController
@RequiredArgsConstructor
@Validated
public class IdpServerRestController {
    private final AccountService accountService;

    @GetMapping(path = "/context", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Account getContextAccount() {
        return accountService.getContextAccount();
    }
}
