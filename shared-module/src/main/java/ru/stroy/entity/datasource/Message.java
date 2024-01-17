package ru.stroy.entity.datasource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.IdEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Message extends IdEntity {
    private String json;
}
