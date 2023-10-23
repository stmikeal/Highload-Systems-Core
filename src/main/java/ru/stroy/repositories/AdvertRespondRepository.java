package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.AdvertRespond;
import ru.stroy.repositories.basic.TimeManagedRepository;

@Repository
public interface AdvertRespondRepository extends TimeManagedRepository<AdvertRespond> {
}
