package ru.stroy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.MapsId;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.CodeEntity;
import ru.stroy.entity.basic.IdEntity;

@Entity(name = "account_role")
@RequiredArgsConstructor
@Getter
@Setter
public class AccountRole extends CodeEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}
