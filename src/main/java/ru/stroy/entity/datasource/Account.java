package ru.stroy.entity.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.TimeManagedEntity;

import java.time.LocalDate;
import java.util.List;

@Entity(name = "account")
@RequiredArgsConstructor
@Getter
@Setter
public class Account extends TimeManagedEntity {

    @Column(name = "name", nullable = false)
    private String name = "";

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "birth", nullable = false)
    private LocalDate birth = LocalDate.of(1980, 1, 1);

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<AccountRoleLink> roles;

    @OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
    private List<AccountCompanyLink> positions;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    private Login login;
}
