package ru.stroy.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AdvertRespondCreateDto {
    private Long advertId;
    @NotBlank
    private String title;
    private String description;
}
