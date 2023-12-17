package ru.stroy.repositories.security;

import org.springframework.data.r2dbc.repository.R2dbcRepository;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import ru.stroy.entity.datasource.Login;
import ru.stroy.repositories.basic.TimeManagedRepository;

import java.util.Optional;

public interface LoginRepository extends R2dbcRepository<Login, Long> {
    Mono<Login> findByUsername(String username);
    Mono<Boolean> existsByUsername(String username);
}
