package ru.stroy.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.CodeEntity;
import ru.stroy.entity.basic.IdEntity;

@Entity(name = "access")
@RequiredArgsConstructor
@Getter
@Setter
public class Access extends CodeEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description")
    private String description;
}
