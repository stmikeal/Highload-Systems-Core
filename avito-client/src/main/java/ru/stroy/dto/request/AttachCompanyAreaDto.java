package ru.stroy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.stroy.dto.enumeration.AreaCompanyEnum;

@AllArgsConstructor
@Getter
@Setter
public class AttachCompanyAreaDto {
    private AreaCompanyEnum area;
}
