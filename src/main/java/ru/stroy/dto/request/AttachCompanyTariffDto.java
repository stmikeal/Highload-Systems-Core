package ru.stroy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.stroy.dto.enumeration.AreaCompanyEnum;
import ru.stroy.dto.enumeration.CurrencyTypeEnum;

@AllArgsConstructor
@Getter
@Setter
public class AttachCompanyTariffDto {
    private CurrencyTypeEnum currency;
    private Long weight;
    private Long price;
}
