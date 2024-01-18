package ru.stroy.services;

import ru.stroy.dto.security.LoginDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.stroy.repositories.security.LoginRepository;

@Service
@RequiredArgsConstructor
public class DatabaseAccountUserDetailsService implements UserDetailsService {
    private final LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return new LoginDetails(loginRepository.findByUsername(username).blockOptional().orElseThrow(() -> new UsernameNotFoundException("User not found")));
    }
}
