package ru.stroy.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.stroy.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {
}
