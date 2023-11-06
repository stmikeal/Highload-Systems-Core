package ru.stroy.entity.datasource;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JoinColumn(name = "account", nullable = false)
    @JsonBackReference
    private Account account;

    @ManyToOne
    @JoinColumn(name = "company", nullable = false)
    @JsonBackReference
    private Company company;

    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "access", referencedColumnName = "code", nullable = false)
    private Access access;
}
