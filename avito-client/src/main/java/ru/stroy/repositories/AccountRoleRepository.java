package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.AccountRole;
import ru.stroy.repositories.basic.CodeEntityRepository;

@Repository
public interface AccountRoleRepository extends CodeEntityRepository<AccountRole> {
}
