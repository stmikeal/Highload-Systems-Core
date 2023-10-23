package ru.stroy.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class DocumentPutDto {
    private Long respondId;
    private String url;
    private String title;
    private String description;
}
