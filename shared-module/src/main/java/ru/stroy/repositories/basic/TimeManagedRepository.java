package ru.stroy.repositories.basic;

import org.springframework.data.repository.NoRepositoryBean;
import ru.stroy.entity.basic.TimeManagedEntity;

@NoRepositoryBean
public interface TimeManagedRepository<T extends TimeManagedEntity> extends IdEntityRepository<T> {

}
