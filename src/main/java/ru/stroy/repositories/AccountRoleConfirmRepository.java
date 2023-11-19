package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.AccountRoleConfirmation;
import ru.stroy.repositories.basic.TimeManagedRepository;

@Repository
public interface AccountRoleConfirmRepository extends TimeManagedRepository<AccountRoleConfirmation> {
}
