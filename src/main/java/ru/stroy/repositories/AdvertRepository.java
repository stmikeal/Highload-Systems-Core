package ru.stroy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stroy.entity.Advert;

@Repository
public interface AdvertRepository extends JpaRepository<Advert, Integer> {
}
