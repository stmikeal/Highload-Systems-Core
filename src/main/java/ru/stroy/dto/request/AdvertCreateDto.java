package ru.stroy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.stroy.dto.enumeration.AdvertTypeEnum;
import ru.stroy.dto.enumeration.CurrencyTypeEnum;

@AllArgsConstructor
@Getter
@Setter
public class AdvertCreateDto {
    private AdvertTypeEnum advertType;
    private Long companyId;
    private String title;
    private String description;
    private Long price;
    private CurrencyTypeEnum currency;
}
