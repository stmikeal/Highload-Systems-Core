package ru.stroy.dto.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum AccountRoleEnum {
    Electrician(0L),
    Plumber(1L),
    Locksmith(2L),
    Lineman(3L),
    Miller(4L),
    Turner(5L);

    public final Long code;
}
