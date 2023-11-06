package ru.stroy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DocumentPutDto {
    @NotNull
    @PositiveOrZero
    private Long respondId;
    @Pattern(regexp = "(?:(?:http)|(?:https)):\\/\\/.+\\.(?:(?:com)|(?:ru))")
    private String url;
    @NotBlank
    private String title;
    private String description;
}
