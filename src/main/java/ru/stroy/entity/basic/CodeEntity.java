package ru.stroy.entity.basic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class CodeEntity extends IdEntity {

    @Column(name = "code", nullable = false)
    private Long code;
}
