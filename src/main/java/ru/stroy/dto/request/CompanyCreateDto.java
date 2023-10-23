package ru.stroy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class CompanyCreateDto {
    private String name;
    private String avatarUrl;
}
