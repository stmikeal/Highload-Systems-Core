package ru.stroy.entity.datasource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.IdEntity;

@Entity(name = "account_role_link")
@RequiredArgsConstructor
@Getter
@Setter
public class AccountRoleLink extends IdEntity {

    @ManyToOne
    @JoinColumn(name = "account")
    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "code", nullable = false)
    private AccountRole role;

    @ManyToOne
    @JoinColumn(name = "confirm", referencedColumnName = "id")
    @JsonIgnore
    private AccountRoleConfirmation confirm;
}
