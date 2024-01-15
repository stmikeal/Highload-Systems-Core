package ru.stroy.dto.request;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class AccountUpdateDto {
    @NotBlank
    private String name;
    @Pattern(regexp = "(?:(?:http)|(?:https))://.+\\.(?:(?:com)|(?:ru))")
    private String avatarUrl;
    @Past
    private LocalDate birth;
    private String email;
}
