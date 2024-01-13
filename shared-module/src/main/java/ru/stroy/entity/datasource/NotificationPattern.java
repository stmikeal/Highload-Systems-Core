package ru.stroy.entity.datasource;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.IdEntity;

@Entity(name = "notification_pattern")
@RequiredArgsConstructor
@Getter
@Setter
public class NotificationPattern extends IdEntity {
    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "pattern")
    private String pattern;
}
