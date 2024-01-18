package ru.stroy.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.stroy.dto.enumeration.AccountRoleEnum;

@AllArgsConstructor
@Getter
@Setter
public class AccountPutConfirmationDto {
    @NotNull
    String signature;
    String description;
    @Min(0L)
    Long documentId;
    @NotNull
    AccountRoleEnum role;
}
