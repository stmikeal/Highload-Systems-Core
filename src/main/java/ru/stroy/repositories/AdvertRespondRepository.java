package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.Account;
import ru.stroy.entity.datasource.Advert;
import ru.stroy.entity.datasource.AdvertRespond;
import ru.stroy.repositories.basic.TimeManagedRepository;

import java.util.Optional;

@Repository
public interface AdvertRespondRepository extends TimeManagedRepository<AdvertRespond> {
    Optional<AdvertRespond> findByApplicantAndAdvert(Account applicant, Advert advert);
}
