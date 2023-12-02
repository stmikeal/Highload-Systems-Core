package ru.stroy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.stroy.dto.enumeration.AccessCompanyEnum;

@AllArgsConstructor
@Getter
@Setter
public class AttachCompanyAccountDto {
    @NotBlank
    private String position;
    @NotNull
    private AccessCompanyEnum access;
    @NotNull
    @PositiveOrZero
    private Long accountId;
}
