package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.Area;
import ru.stroy.repositories.basic.CodeEntityRepository;

@Repository
public interface AreaRepository extends CodeEntityRepository<Area> {
}
