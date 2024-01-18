package ru.stroy.repositories.basic;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import ru.stroy.entity.basic.IdEntity;


@NoRepositoryBean
public interface IdEntityRepository<T extends IdEntity> extends JpaRepository<T, Long> {
}
