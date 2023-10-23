package ru.stroy.dto.request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class AccountUpdateDto {
    private String name;
    private String avatarUrl;
    private LocalDate birth;
}
