package ru.stroy.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.IdEntity;

@Entity(name = "account_company_link")
@RequiredArgsConstructor
@Getter
@Setter
public class AccountCompanyLink extends IdEntity {

    @ManyToOne
    @JoinColumn(name = "account", referencedColumnName = "id", nullable = false)
    private Account account;

    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "id", nullable = false)
    private Company company;

    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "access", referencedColumnName = "code", nullable = false)
    private Access access;
}
