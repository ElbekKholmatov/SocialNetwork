package com.instagram.instagram.repository;

import com.instagram.instagram.domains.basic.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface DocumentRepository extends JpaRepository<Document, Long> {
    @Query("select d from Document d where d.path = ?1")
    Optional<Document> findByPath(String fileName);
}