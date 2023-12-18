import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import ru.stroy.feign.IdpClientFeignClient;
import ru.stroy.repositories.AccountRepository;
import ru.stroy.services.AccountService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static util.ResourceContainer.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class AccountTest {

    private static AccountService accountService;

    private static final IdpClientFeignClient idpClientFeignClient = mock(IdpClientFeignClient.class);
    private static final AccountRepository accountRepository = mock(AccountRepository.class);

    @BeforeAll
    static void init() {
        when(accountRepository.findById(testAccount1.getId()))
                .thenReturn(Mono.just(testAccount1));
        when(accountRepository.findById(falseId))
                .thenReturn(Mono.empty());
    }

    @BeforeEach
    void refresh() {
        accountService = new AccountService(
                accountRepository,
                idpClientFeignClient
        );
    }

    @Test
    public void checkGetAccountById() {
        Assertions.assertEquals(testAccount1.getId(), accountService.getAccountById(testAccount1.getId()).block().getId());
    }

    @Test
    public void checkApproveAccount() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.approveAccount(falseId));
    }

    @Test
    public void checkFireAccount() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> accountService.fireAccount(falseId));
    }

}
