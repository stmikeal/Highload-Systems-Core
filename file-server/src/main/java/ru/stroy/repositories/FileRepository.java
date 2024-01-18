package ru.stroy.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.FileAttachment;
import ru.stroy.repositories.basic.IdEntityRepository;

@Repository
public interface FileRepository extends IdEntityRepository<FileAttachment> {

    @Query(nativeQuery = true, value = "select * from attached_file " +
            "where (select distinct login.account from login where login.username = :username) = author")
    Page<FileAttachment> findAllWithAuthor(Pageable pageable, String username);
}
