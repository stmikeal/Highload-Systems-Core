package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.Access;
import ru.stroy.repositories.basic.CodeEntityRepository;

@Repository
public interface AccessRepository extends CodeEntityRepository<Access> {
}
