package ru.stroy.entity.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.TimeManagedEntity;

@Entity(name = "document")
@RequiredArgsConstructor
@Getter
@Setter
public class Document extends TimeManagedEntity {

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id", nullable = false)
    private Account author;

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;
}
