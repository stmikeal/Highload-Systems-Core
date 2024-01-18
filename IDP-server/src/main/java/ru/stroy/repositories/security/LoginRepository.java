package ru.stroy.repositories.security;

import ru.stroy.entity.datasource.Login;
import ru.stroy.repositories.basic.TimeManagedRepository;

import java.util.Optional;

public interface LoginRepository extends TimeManagedRepository<Login> {
    Optional<Login> findByUsername(String username);

    Boolean existsByUsername(String username);
}
