package ru.stroy.entity.basic;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
@Setter
@MappedSuperclass
public class TimeManagedEntity extends IdEntity {

    @Column(name = "modified_timestamp", nullable = false)
    private LocalDateTime modifiedTimestamp;

    @Column(name = "created_timestamp", nullable = false)
    private LocalDateTime createdTimestamp;

    @PrePersist
    protected void onCreate() {
        createdTimestamp = LocalDateTime.now();
        modifiedTimestamp = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        modifiedTimestamp = LocalDateTime.now();
    }
}
