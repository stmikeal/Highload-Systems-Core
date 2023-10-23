package ru.stroy.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.stroy.entity.datasource.Document;
import ru.stroy.repositories.basic.TimeManagedRepository;

import java.util.List;

@Repository
public interface DocumentRepository extends TimeManagedRepository<Document> {

    @Query(value = "insert into document_respond_link (respond, document) values ($documentId, $respondId)",
            nativeQuery = true)
    void attachToRespond(Long documentId, Long respondId);

    @Query(value = "select * from document as d where d.id in " +
            "(select link.document from document_respond_link as link where link.respond in " +
            "(select respond.id from advert_respond as respond where respond.applicant = $userId))",
            nativeQuery = true)
    List<Document> getApplicantDocuments(Long userId);
}
