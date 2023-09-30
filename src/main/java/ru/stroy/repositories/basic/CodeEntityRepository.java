package ru.stroy.repositories.basic;

import org.springframework.data.repository.NoRepositoryBean;
import ru.stroy.entity.basic.CodeEntity;

import java.util.Optional;

@NoRepositoryBean
public interface CodeEntityRepository<T extends CodeEntity> extends IdEntityRepository<T> {

    Optional<T> findByCode(Long code);
}
