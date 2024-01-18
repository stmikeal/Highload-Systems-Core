package ru.stroy.repositories;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.Account;

@Repository
public interface AccountRepository extends R2dbcRepository<Account, Long> {
}
