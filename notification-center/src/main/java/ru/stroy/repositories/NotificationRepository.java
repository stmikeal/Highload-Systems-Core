package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.NotificationPattern;
import ru.stroy.repositories.basic.IdEntityRepository;

import java.util.Optional;

@Repository
public interface NotificationRepository extends IdEntityRepository<NotificationPattern> {
    Optional<NotificationPattern> findByName(String name);
}
