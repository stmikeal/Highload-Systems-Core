package ru.stroy.entity.datasource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.stroy.entity.basic.IdEntity;

@Entity(name = "attached_file")
@RequiredArgsConstructor
@Getter
@Setter
public class FileAttachment extends IdEntity {

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "bytes", nullable = false)
    @JsonIgnore
    byte[] bytes;

    @ManyToOne
    @JoinColumn(name = "author", referencedColumnName = "id", nullable = false)
    @JsonIgnore
    private Account author;

}
