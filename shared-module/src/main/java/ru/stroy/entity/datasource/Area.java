package ru.stroy.entity.datasource;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.CodeEntity;

import java.util.List;

@Entity(name = "area")
@RequiredArgsConstructor
@Getter
@Setter
public class Area extends CodeEntity {

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @ManyToMany
    @JoinTable(name = "company_area_link",
            joinColumns = @JoinColumn(name = "area"),
            inverseJoinColumns = @JoinColumn(name = "company"))
    private List<Company> companies;
}
