package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.AccountCompanyLink;
import ru.stroy.repositories.basic.IdEntityRepository;

@Repository
public interface AccountCompanyLinkRepository extends IdEntityRepository<AccountCompanyLink> {
    Boolean existsAccountCompanyLinkByAccount_IdAndCompany_IdAndAccess_Code(Long accountId, Long companyId, Long accessCode);
}
