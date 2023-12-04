package ru.stroy.entity.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.TimeManagedEntity;

@Entity(name = "tariff")
@RequiredArgsConstructor
@Getter
@Setter
public class Tariff extends TimeManagedEntity {
    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "id", nullable = false)
    private Company company;

    @Column(name = "limit_weight")
    private Long limit;

    @Column(name = "price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "currency", referencedColumnName = "code")
    private CurrencyType currency;
}
