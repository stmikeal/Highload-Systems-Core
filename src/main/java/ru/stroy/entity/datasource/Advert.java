package ru.stroy.entity.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.TimeManagedEntity;

@Entity(name = "advert")
@RequiredArgsConstructor
@Getter
@Setter
public class Advert extends TimeManagedEntity {

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id", nullable = false)
    private Account author;

    @ManyToOne
    @JoinColumn(name = "company", referencedColumnName = "id", nullable = false)
    private Company company;

    @ManyToOne
    @JoinColumn(name = "type", referencedColumnName = "code", nullable = false)
    private AdvertType type;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;

    @ManyToOne
    @JoinColumn(name = "currency", referencedColumnName = "code")
    private CurrencyType currency;
}
