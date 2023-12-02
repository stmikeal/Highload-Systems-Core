package ru.stroy.entity.datasource;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.CodeEntity;
import ru.stroy.entity.basic.IdEntity;

@Entity(name = "currency_type")
@RequiredArgsConstructor
@Getter
@Setter

public class CurrencyType extends CodeEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "short_name", nullable = false)
    private String shortName;
}
