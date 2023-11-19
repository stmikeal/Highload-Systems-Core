package ru.stroy.entity.datasource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.TimeManagedEntity;

import java.time.LocalDateTime;

@Entity(name = "login")
@RequiredArgsConstructor
@Getter
@Setter
public class Login extends TimeManagedEntity {

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account", referencedColumnName = "id", nullable = false)
    private Account account;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    @Column(name = "last_auth")
    private LocalDateTime lastAuth;
}
