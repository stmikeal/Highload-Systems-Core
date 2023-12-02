package ru.stroy.module;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.stroy.dto.enumeration.AccessCompanyEnum;
import ru.stroy.repositories.AccessRepository;
import ru.stroy.repositories.AccountCompanyLinkRepository;
import ru.stroy.services.AccountCompanyLinkService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ru.stroy.module.utils.ResourceContainer.*;

@ExtendWith(MockitoExtension.class)
public class AccountCompanyTest {
    private static AccountCompanyLinkService accountCompanyLinkService;

    private static final AccountCompanyLinkRepository accountCompanyLinkRepository = mock(AccountCompanyLinkRepository.class);
    private static final AccessRepository accessRepository = mock(AccessRepository.class);

    @BeforeAll
    static void init() {
        when(accountCompanyLinkRepository.existsAccountCompanyLinkByAccount_IdAndCompany_IdAndAccess_Code(
                testAccount1.getId(), testCompany1.getId(), AccessCompanyEnum.Owner.getCode()
        )).thenReturn(true);
        when(accountCompanyLinkRepository.existsAccountCompanyLinkByAccount_IdAndCompany_IdAndAccess_Code(
                testAccount2.getId(), testCompany1.getId(), AccessCompanyEnum.Moderator.getCode()
        )).thenReturn(true);
    }

    @BeforeEach
    void refresh() { accountCompanyLinkService = new AccountCompanyLinkService(accountCompanyLinkRepository, accessRepository); }

    @Test
    public void checkPermissionOfCompanyOwner() {
        Assertions.assertTrue(accountCompanyLinkService.hasEditPermission(testAccount1.getId(), testCompany1.getId()));
    }

    @Test
    public void checkPermissionOfCompanyModerator() {
        Assertions.assertTrue(accountCompanyLinkService.hasEditPermission(testAccount2.getId(), testCompany1.getId()));
    }

    @Test
    public void checkPermissionOfCompanyNotPermittedPersons() {
        Assertions.assertFalse(accountCompanyLinkService.hasEditPermission(testAccount3.getId(), testCompany1.getId()));
    }
}
