import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import reactor.core.publisher.Mono;
import ru.stroy.repositories.security.LoginRepository;
import ru.stroy.services.DatabaseAccountUserDetailsService;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static util.ResourceContainer.*;

@ExtendWith(MockitoExtension.class)
@RunWith(MockitoJUnitRunner.class)
public class DatabaseAccountUserDetailsServiceTest {

    private static DatabaseAccountUserDetailsService databaseAccountUserDetailsService;

    private static final LoginRepository loginRepository = mock(LoginRepository.class);

    @BeforeAll
    static void init() {
        when(loginRepository.findByUsername(falseUsername))
                .thenReturn(Mono.empty());
        when(loginRepository.findByUsername(trueUsername))
                .thenReturn(Mono.just(testLogin1));
    }

    @BeforeEach
    void refresh() {
        databaseAccountUserDetailsService = new DatabaseAccountUserDetailsService(
                loginRepository
        );
    }

    @Test
    public void checkFalseUsername() {
        Assertions.assertThrows(UsernameNotFoundException.class,
                () -> databaseAccountUserDetailsService.loadUserByUsername(falseUsername));
    }

}
