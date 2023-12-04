package ru.stroy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stroy.services.AccountService;

@RestController
@RequiredArgsConstructor
@Validated
public class AuthenticationController {

    private final AccountService accountService;

    @GetMapping("/approve/{id}")
    @Secured("ADMIN_ROLE")
    public void approveToModerator(@PathVariable Long id) {
        accountService.approveAccount(id);
    }

    @GetMapping("/fire/{id}")
    @Secured("ADMIN_ROLE")
    public void fireFromModerator(@PathVariable Long id) {
        accountService.fireAccount(id);
    }
}
