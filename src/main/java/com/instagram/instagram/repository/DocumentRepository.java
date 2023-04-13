package com.instagram.instagram.repository;

import com.instagram.instagram.domains.basic.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}