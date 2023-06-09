package com.instagram.instagram.repository;

import com.instagram.instagram.domains.basic.Document;
import com.instagram.instagram.dto.ReturnDocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.net.URI;
import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("select d from Document d where d.path = ?1")
    Optional<Document> findByPath(String fileName);

    @Query("select d from Document d where d.createdBy = ?1")
    Page<Document> findAllByCreatedBy(Long id, Pageable pageable);

    @Query("select d from Document d where d.generatedName = ?1")

    Optional<Document> findByName(String fileName);
    @Query("select d from Document d where d.generatedName = ?1")
    Document findByNameLink(String fileName);
}