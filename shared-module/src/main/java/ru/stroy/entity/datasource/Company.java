package ru.stroy.entity.datasource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.TimeManagedEntity;

import java.util.List;

@Entity(name = "company")
@RequiredArgsConstructor
@Getter
@Setter
public class Company extends TimeManagedEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<AccountCompanyLink> employee;

    @ManyToMany
    @JoinTable(name = "company_area_link",
            joinColumns = @JoinColumn(name = "company"),
            inverseJoinColumns = @JoinColumn(name = "area"))
    @JsonIgnore
    private List<Area> areas;
}
