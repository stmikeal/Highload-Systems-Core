package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.Account;
import ru.stroy.repositories.basic.TimeManagedRepository;

@Repository
public interface AccountRepository extends TimeManagedRepository<Account> {
}
