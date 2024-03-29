package ru.stroy.dto.security;

import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.stroy.dto.enumeration.PermissionAccountEnum;
import ru.stroy.entity.datasource.Login;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

@AllArgsConstructor
public class LoginDetails implements UserDetails {
    private final Login login;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Objects.requireNonNull(PermissionAccountEnum.getByCode(login.getAccount().getPermission())).getName()));
    }

    @Override
    public String getPassword() {
        return login.getPassword();
    }

    @Override
    public String getUsername() {
        return login.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
