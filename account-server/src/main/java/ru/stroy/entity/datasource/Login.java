package ru.stroy.entity.datasource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Table("login")
@RequiredArgsConstructor
@Getter
@Setter
public class Login {

    @Id
    private Long id;

    @Column("username")
    private String username;

    @Column("account")
    private Account account;

    @Column("password")
    @JsonIgnore
    private String password;

    @Column("last_auth")
    private LocalDateTime lastAuth;
}
