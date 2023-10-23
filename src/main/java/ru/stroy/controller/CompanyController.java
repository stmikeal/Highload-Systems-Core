package ru.stroy.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.stroy.dto.request.AttachCompanyAccountDto;
import ru.stroy.dto.request.CompanyCreateDto;
import ru.stroy.services.CompanyService;

@RequiredArgsConstructor
@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;

    @PutMapping
    public void createCompany(@RequestBody CompanyCreateDto companyCreateDto) {
        companyService.createCompanyByCurrentUser(companyCreateDto);
    }

    @PutMapping("/{id}")
    public void attachEmployee(@PathVariable Long id, @RequestBody AttachCompanyAccountDto attachCompanyAccountDto) {
        companyService.attachEmployeeToCompany(id, attachCompanyAccountDto);
    }
}
