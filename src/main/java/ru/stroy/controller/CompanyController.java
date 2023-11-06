package ru.stroy.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stroy.dto.request.AttachCompanyAccountDto;
import ru.stroy.dto.request.AttachCompanyAreaDto;
import ru.stroy.dto.request.AttachCompanyTariffDto;
import ru.stroy.dto.request.CompanyCreateDto;
import ru.stroy.services.CompanyService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/company")
@Validated
public class CompanyController {
    private final CompanyService companyService;

    @PutMapping
    public void createCompany(@Valid @RequestBody CompanyCreateDto companyCreateDto) {
        companyService.createCompanyByCurrentUser(companyCreateDto);
    }

    @PostMapping("/account/{id}")
    public void attachEmployee(@PositiveOrZero @PathVariable Long id,
                               @Valid @RequestBody AttachCompanyAccountDto attachCompanyAccountDto) {
        companyService.attachEmployeeToCompany(id, attachCompanyAccountDto);
    }

    @PostMapping("/area/{id}")
    public void attachArea(@PositiveOrZero @PathVariable Long id,
                           @Valid @RequestBody AttachCompanyAreaDto attachCompanyAreaDto) {
        companyService.attachAreaToCompany(id, attachCompanyAreaDto);
    }

    @PostMapping("/tariff/{id}")
    public void attachTariff(@PositiveOrZero @PathVariable Long id,
                             @Valid @RequestBody AttachCompanyTariffDto attachCompanyTariffDto) {
        companyService.attachTariffToCompany(id, attachCompanyTariffDto);
    }
}
