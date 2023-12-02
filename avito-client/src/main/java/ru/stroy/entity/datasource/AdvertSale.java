package ru.stroy.entity.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.TimeManagedEntity;

@Entity(name = "advert_sale")
@RequiredArgsConstructor
@Getter
@Setter
public class AdvertSale extends TimeManagedEntity {

    @ManyToOne
    @JoinColumn(name = "applicant", referencedColumnName = "id", nullable = false)
    private Account applicant;

    @ManyToOne
    @JoinColumn(name = "advert", referencedColumnName = "id", nullable = false)
    private Advert advert;

    @ManyToOne
    @JoinColumn(name = "delivery", referencedColumnName = "id", nullable = false)
    private Company delivery;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "currency", referencedColumnName = "code")
    private CurrencyType currency;
}
