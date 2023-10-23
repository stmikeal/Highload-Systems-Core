package ru.stroy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.stroy.dto.enumeration.AccessCompanyEnum;

@AllArgsConstructor
@Getter
@Setter
public class AttachCompanyAccountDto {
    private String position;
    private AccessCompanyEnum access;
    private Long accountId;
}
