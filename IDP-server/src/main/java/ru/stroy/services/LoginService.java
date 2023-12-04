package ru.stroy.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.stroy.dto.request.RegisterRequestDto;
import ru.stroy.entity.datasource.Login;

@Service
@RequiredArgsConstructor
public class LoginService {
    @Autowired
    private PasswordEncoder passwordEncoder;
    private final AccountService accountService;

    @Transactional
    public Login createLoginFromRequest(RegisterRequestDto requestLogin) {
        Login login = new Login();
        login.setUsername(requestLogin.getUsername());
        login.setPassword(passwordEncoder.encode(requestLogin.getPassword()));
        login.setAccount(accountService.createEmptyAccount());
        return login;
    }
}
