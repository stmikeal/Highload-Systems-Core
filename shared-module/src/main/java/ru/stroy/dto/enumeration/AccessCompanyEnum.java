package ru.stroy.dto.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccessCompanyEnum {
    Owner(0L),
    Moderator(1L),
    Operator(2L),
    Employee(3L);

    public final Long code;
}
