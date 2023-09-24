package ru.stroy.entity;

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

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "account")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "role", referencedColumnName = "code", nullable = false)
    private AccountRole role;

    @ManyToOne
    @JoinColumn(name = "confirm", referencedColumnName = "id")
    private AccountRoleConfirmation confirm;
}
