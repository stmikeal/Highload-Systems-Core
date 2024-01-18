package ru.stroy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stroy.services.AccountService;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Validated
public class AccountServerRestController {
    private final AccountService accountService;

    @PutMapping(path = "/empty", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Long createEmptyAccount() {
        return Objects.requireNonNull(accountService.createEmptyAccount().block()).getId();
    }
}
