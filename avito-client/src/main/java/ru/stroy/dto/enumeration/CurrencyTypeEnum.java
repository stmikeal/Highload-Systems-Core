package ru.stroy.dto.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum CurrencyTypeEnum {
    Rub(0L),
    Usd(1L),
    Euro(2L),
    Thousand(3L),
    Million(4L);

    public final Long code;
}
