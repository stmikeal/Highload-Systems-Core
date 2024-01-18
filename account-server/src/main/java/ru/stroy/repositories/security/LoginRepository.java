package ru.stroy.repositories.security;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import reactor.core.publisher.Mono;
import ru.stroy.entity.datasource.Login;

public interface LoginRepository extends R2dbcRepository<Login, Long> {
    Mono<Login> findByUsername(String username);
}
