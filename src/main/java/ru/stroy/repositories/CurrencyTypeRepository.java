package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.CurrencyType;
import ru.stroy.repositories.basic.CodeEntityRepository;

@Repository
public interface CurrencyTypeRepository extends CodeEntityRepository<CurrencyType> {
}
