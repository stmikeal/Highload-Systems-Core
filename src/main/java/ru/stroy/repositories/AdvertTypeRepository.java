package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.AdvertType;
import ru.stroy.repositories.basic.CodeEntityRepository;

@Repository
public interface AdvertTypeRepository extends CodeEntityRepository<AdvertType> {
}
