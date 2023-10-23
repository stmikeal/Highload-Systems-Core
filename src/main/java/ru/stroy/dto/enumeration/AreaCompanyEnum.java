package ru.stroy.dto.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AreaCompanyEnum {
    SPb(0L),
    Moscow(1L),
    MoscowState(2L),
    Tomsk(3L);

    public final Long code;
}
