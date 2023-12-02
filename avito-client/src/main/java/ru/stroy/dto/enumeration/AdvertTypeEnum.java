package ru.stroy.dto.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AdvertTypeEnum {
    FindEmployee(0L),
    ResumeEmployee(1L),
    Sale(2L),
    Rent(3L),
    Purchase(4L);

    public final Long code;
}
