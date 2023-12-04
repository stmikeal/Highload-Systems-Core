package ru.stroy.repositories.basic;

import org.springframework.data.repository.NoRepositoryBean;
import ru.stroy.entity.basic.CodeEntity;

@NoRepositoryBean
public interface CodeEntityRepository<T extends CodeEntity> extends IdEntityRepository<T> {

    T findByCode(Long code);
}
