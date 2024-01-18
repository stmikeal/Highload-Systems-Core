package ru.stroy.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import ru.stroy.dto.request.AccountUpdateDto;
import ru.stroy.entity.datasource.Account;
import ru.stroy.services.AccountService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
@Validated
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    @ResponseBody
    public Mono<Account> getAccountInfo() {
        return accountService.getContextAccount();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Mono<Account> getOtherAccountInfo(@PositiveOrZero @PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    public void setAccountInfo(@Valid @RequestBody AccountUpdateDto accountUpdateDto) {
        accountService.updateAccountByDto(accountUpdateDto);
    }

}
