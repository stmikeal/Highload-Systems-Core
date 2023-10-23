package ru.stroy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.stroy.dto.request.AccountUpdateDto;
import ru.stroy.entity.datasource.Account;
import ru.stroy.services.AccountService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/account")
public class AccountController {
    private final AccountService accountService;

    @GetMapping
    @ResponseBody
    public Account getAccountInfo() {
       return accountService.getContextAccount();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Account getOtherAccountInfo(@PathVariable Long id) {
        return accountService.getAccountById(id);
    }

    @PostMapping
    @ResponseBody
    public void setAccountInfo(@RequestBody AccountUpdateDto accountUpdateDto) {
        accountService.updateAccountByDto(accountUpdateDto);
    }
}
