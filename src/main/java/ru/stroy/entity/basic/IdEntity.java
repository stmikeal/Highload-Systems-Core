package ru.stroy.entity.basic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@RequiredArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class IdEntity implements Serializable {

    @Id
    @Column(name = "id")
    private Long id;

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        return ((IdEntity) o).getId().equals(this.id);
    }
}
