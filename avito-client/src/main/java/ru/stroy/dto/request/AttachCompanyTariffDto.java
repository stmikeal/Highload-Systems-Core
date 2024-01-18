package ru.stroy.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.stroy.dto.enumeration.CurrencyTypeEnum;

@AllArgsConstructor
@Getter
@Setter
public class AttachCompanyTariffDto {
    @NotNull
    private CurrencyTypeEnum currency;
    @NotNull
    @Positive
    private Long weight;
    @NotNull
    @Min(1L)
    private Long price;
}
