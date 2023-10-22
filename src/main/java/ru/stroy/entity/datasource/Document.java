package ru.stroy.entity.datasource;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.TimeManagedEntity;

@Entity(name = "document")
@RequiredArgsConstructor
@Getter
@Setter
public class Document extends TimeManagedEntity {

    @Column(name = "url", nullable = false)
    private String url;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;
}
