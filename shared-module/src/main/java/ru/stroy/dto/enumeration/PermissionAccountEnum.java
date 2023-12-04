package ru.stroy.dto.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum PermissionAccountEnum {
    User(0L, "USER_ROLE"),
    Moderator(1L, "MODERATOR_ROLE"),
    Admin(2L, "ADMIN_ROLE");

    PermissionAccountEnum(Long code) {
        this.code = code;
        this.name = names[code.intValue()];
    }

    private final String[] names = {"USER_ROLE", "MODERATOR_ROLE", "ADMIN_ROLE"};

    public static PermissionAccountEnum getByCode(Long code) {
        for (var value : PermissionAccountEnum.values()) {
            if (value.getCode().equals(code)) return value;
        }
        return null;
    }

    private final Long code;
    private final String name;
}
