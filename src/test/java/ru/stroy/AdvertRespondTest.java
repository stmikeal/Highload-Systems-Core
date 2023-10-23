package ru.stroy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.AdvertRespond;
import ru.stroy.repositories.AdvertRespondRepository;
import ru.stroy.services.AccountService;
import ru.stroy.services.AdvertRespondService;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AdvertRespondTest {
    private static AdvertRespondService advertRespondService;

    @Mock
    private static AccountService accountService;
    @Mock
    private static AdvertRespondRepository advertRespondRepository;

    @BeforeAll
    static void init() {
        advertRespondService = new AdvertRespondService(accountService, advertRespondRepository);
    }

    @Test
    public void checkPositiveAuthorities() {
        Account account = new Account();
        account.setId(321L);
        when(accountService.getContextAccount()).thenReturn(account);
        AdvertRespond advertRespond = new AdvertRespond();
        advertRespond.setApplicant(account);
        when(advertRespondRepository.findById(777L)).thenReturn(Optional.of(advertRespond));
        Assertions.assertTrue(advertRespondService.isOwnedRespond(777L));
    }

    @Test
    public void checkNegativeAuthorities() {
        Account account = new Account();
        account.setId(321L);
        Account account2 = new Account();
        account2.setId(123L);
        when(accountService.getContextAccount()).thenReturn(account);
        AdvertRespond advertRespond = new AdvertRespond();
        advertRespond.setApplicant(account2);
        when(advertRespondRepository.findById(777L)).thenReturn(Optional.of(advertRespond));
        Assertions.assertFalse(advertRespondService.isOwnedRespond(777L));
    }
}
