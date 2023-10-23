package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.Advert;
import ru.stroy.repositories.basic.TimeManagedRepository;

@Repository
public interface AdvertRepository extends TimeManagedRepository<Advert> {
}
