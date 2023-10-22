package ru.stroy.entity.datasource;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.CodeEntity;

@Entity(name = "advert_type")
@RequiredArgsConstructor
@Getter
@Setter
public class AdvertType extends CodeEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;
}
