package ru.stroy.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import ru.stroy.dto.enumeration.AdvertTypeEnum;
import ru.stroy.dto.enumeration.CurrencyTypeEnum;

@AllArgsConstructor
@Getter
@Setter
public class AdvertCreateDto {
    @NotNull
    private AdvertTypeEnum advertType;
    private Long companyId;
    @NotBlank
    private String title;
    private String description;
    @Min(1L)
    private Long price;
    private CurrencyTypeEnum currency;
}
