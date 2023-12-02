package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.AccountRoleLink;
import ru.stroy.repositories.basic.IdEntityRepository;

@Repository
public interface AccountRoleLinkRepository extends IdEntityRepository<AccountRoleLink> {
}
