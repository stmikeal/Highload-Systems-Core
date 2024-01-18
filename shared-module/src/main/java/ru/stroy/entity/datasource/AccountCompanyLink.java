package ru.stroy.entity.datasource;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Account account;

    @ManyToOne
    @JoinColumn(name = "company", nullable = false)
    @JsonIgnore
    private Company company;

    @Column(name = "position")
    private String position;

    @ManyToOne
    @JoinColumn(name = "access", referencedColumnName = "code", nullable = false)
    @JsonIgnore
    private Access access;
}
