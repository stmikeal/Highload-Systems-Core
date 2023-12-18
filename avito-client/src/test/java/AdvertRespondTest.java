import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.stroy.repositories.AdvertRespondRepository;
import ru.stroy.services.AccountService;
import ru.stroy.services.AdvertRespondService;

import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static util.ResourceContainer.*;



@ExtendWith(MockitoExtension.class)
public class AdvertRespondTest {
    private static AdvertRespondService advertRespondService;


    private static final AccountService accountService = mock(AccountService.class);
    private static final AdvertRespondRepository advertRespondRepository = mock(AdvertRespondRepository.class);

    @BeforeAll
    static void init() {
        when(accountService.getContextAccount())
                .thenReturn(testAccount1);
        when(advertRespondRepository.findById(testAdvertRespondApplicant1.getId()))
                .thenReturn(Optional.of(testAdvertRespondApplicant1));
        when(advertRespondRepository.findById(testAdvertRespondApplicant2.getId()))
                .thenReturn(Optional.of(testAdvertRespondApplicant2));
    }

    @BeforeEach
    void refresh() {advertRespondService = new AdvertRespondService(advertRespondRepository, accountService);}

    @Test
    public void checkPositiveAuthorities() {
        Assertions.assertTrue(advertRespondService.isOwnedRespond(testAdvertRespondApplicant1.getId()));
    }

    @Test
    public void checkNegativeAuthorities() {
        Assertions.assertFalse(advertRespondService.isOwnedRespond(testAdvertRespondApplicant2.getId()));
    }
}
