package com.instagram.instagram.repos;

import com.instagram.instagram.domains.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository<Document, ID> extends JpaRepository<Document, ID> {

}