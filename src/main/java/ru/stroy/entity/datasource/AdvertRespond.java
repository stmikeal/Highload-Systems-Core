package ru.stroy.entity.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.TimeManagedEntity;

import java.util.List;

@Entity(name = "advert_respond")
@RequiredArgsConstructor
@Getter
@Setter
public class AdvertRespond extends TimeManagedEntity {

    @ManyToOne
    @JoinColumn(name = "applicant", referencedColumnName = "id", nullable = false)
    private Account applicant;

    @ManyToOne
    @JoinColumn(name = "advert", referencedColumnName = "id", nullable = false)
    private Advert advert;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String text;

    @ManyToMany
    @JoinTable(name="document_respond_link",
            joinColumns=@JoinColumn(name="respond"),
            inverseJoinColumns=@JoinColumn(name="document"))
    private List<Document> documents;
}
