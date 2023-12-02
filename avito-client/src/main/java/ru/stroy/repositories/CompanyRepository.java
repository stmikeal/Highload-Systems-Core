package ru.stroy.repositories;

import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.Company;
import ru.stroy.repositories.basic.TimeManagedRepository;

@Repository
public interface CompanyRepository extends TimeManagedRepository<Company> {
}
