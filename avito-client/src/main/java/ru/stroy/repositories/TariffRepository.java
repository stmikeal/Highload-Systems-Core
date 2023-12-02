package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.Tariff;
import ru.stroy.repositories.basic.TimeManagedRepository;

@Repository
public interface TariffRepository extends TimeManagedRepository<Tariff> {
}
