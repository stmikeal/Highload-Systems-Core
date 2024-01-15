package ru.stroy.entity.datasource;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ru.stroy.entity.basic.TimeManagedEntity;

import java.time.LocalDate;

@Table("account")
@RequiredArgsConstructor
@Getter
@Setter
public class Account {

    @Id
    private Long id;

    @Column("name")
    private String name = "";

    @Column("avatar_url")
    private String avatarUrl = "";

    @Column("birth")
    private LocalDate birth = LocalDate.of(1980, 1, 1);

    @Column("permission")
    private Long permission = 0L;

    @Column("email")
    private String email;

}
