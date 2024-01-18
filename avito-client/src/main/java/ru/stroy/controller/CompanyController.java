package ru.stroy.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.stroy.dto.request.AttachCompanyAccountDto;
import ru.stroy.dto.request.AttachCompanyAreaDto;
import ru.stroy.dto.request.AttachCompanyTariffDto;
import ru.stroy.dto.request.CompanyCreateDto;
import ru.stroy.entity.datasource.Company;
import ru.stroy.repositories.CompanyRepository;
import ru.stroy.services.CompanyService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/company")
@Validated
@Tag(name = "Company", description = "Module for working with companies")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyRepository companyRepository;

    @Operation(summary = "Create new company")
    @PutMapping
    public void createCompany(@Valid @RequestBody CompanyCreateDto companyCreateDto) {
        companyService.createCompanyByCurrentUser(companyCreateDto);
    }

    @Operation(summary = "Get list of companies")
    @GetMapping
    public Page<Company> getAll(
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "20") @Min(1) @Max(50) Integer limit
    ) {
        return companyRepository.findAll(PageRequest.of(offset, limit));
    }

    @Operation(summary = "Add new employee to your company")
    @PostMapping("/account/{id}")
    public void attachEmployee(@PositiveOrZero @PathVariable Long id,
                               @Valid @RequestBody AttachCompanyAccountDto attachCompanyAccountDto) {
        companyService.attachEmployeeToCompany(id, attachCompanyAccountDto);
    }

    @Operation(summary = "Attach new area to company")
    @PostMapping("/area/{id}")
    public void attachArea(@PositiveOrZero @PathVariable Long id,
                           @Valid @RequestBody AttachCompanyAreaDto attachCompanyAreaDto) {
        companyService.attachAreaToCompany(id, attachCompanyAreaDto);
    }

    @Operation(summary = "Add new tariff to company")
    @PostMapping("/tariff/{id}")
    public void attachTariff(@PositiveOrZero @PathVariable Long id,
                             @Valid @RequestBody AttachCompanyTariffDto attachCompanyTariffDto) {
        companyService.attachTariffToCompany(id, attachCompanyTariffDto);
    }
}
