package ru.stroy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.access.AccessDeniedException;
import ru.stroy.repositories.AreaRepository;
import ru.stroy.repositories.CompanyRepository;
import ru.stroy.services.AccountCompanyLinkService;
import ru.stroy.services.AccountService;
import ru.stroy.services.CompanyService;
import ru.stroy.services.TariffService;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.stroy.utils.ResourceContainer.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class CompanyTest {
    private static CompanyService companyService;

    private static final AccountCompanyLinkService accountCompanyLinkService = mock(AccountCompanyLinkService.class);
    private static final CompanyRepository companyRepository = mock(CompanyRepository.class);
    private static final AccountService accountService = mock(AccountService.class);
    private static final AreaRepository areaRepository = mock(AreaRepository.class);
    private static final TariffService tariffService = mock(TariffService.class);

    @BeforeAll
    static void init() {
        when(companyRepository.findById(testCompany1.getId()))
                .thenReturn(Optional.of(testCompany1));
        when(companyRepository.findById(testCompany2.getId()))
                .thenReturn(Optional.of(testCompany2));
        when(accountCompanyLinkService.hasEditPermission(testCompany1.getId(), testAccount1.getId()))
                .thenReturn(true);
        when(accountService.getContextAccount()).thenReturn(testAccount1);
    }

    @BeforeEach
    void refresh() {
        companyService = new CompanyService(
                accountCompanyLinkService,
                companyRepository,
                accountService,
                areaRepository,
                tariffService
        );
    }

    @Test
    public void checkEditPermissionOnAdmin() {
        Assertions.assertEquals(testCompany1, companyService.getCompanyWithPermission(testCompany1.getId()));
    }

    @Test
    public void checkEditPermissionOnUnknownCompany() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> companyService.getCompanyWithPermission(testCompany1.getId() + 1));
    }

    @Test
    public void checkEditPermissionOnNotPermitted() {
        Assertions.assertThrows(AccessDeniedException.class, () -> companyService.getCompanyWithPermission(testCompany2.getId()));
    }

}
