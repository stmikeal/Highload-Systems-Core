package ru.stroy.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DocumentPutDto {
    @Pattern(regexp = "(?:(?:http)|(?:https)):\\/\\/.+\\.(?:(?:com)|(?:ru))")
    private String url;
    @NotBlank
    private String title;
    private String description;
}
