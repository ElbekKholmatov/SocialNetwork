package com.instagram.instagram.service;

import com.instagram.instagram.config.GlobalExceptionHandler;
import com.instagram.instagram.config.security.SessionUser;
import com.instagram.instagram.domains.Link;
import com.instagram.instagram.domains.Saved;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.domains.basic.Document;
import com.instagram.instagram.dto.ReturnDocumentDTO;
import com.instagram.instagram.firebase.MediaService;
import com.instagram.instagram.mapper.DocumentMapper;
import com.instagram.instagram.repository.DocumentRepository;
import com.instagram.instagram.repository.UserRepository;
import jakarta.persistence.Access;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static com.instagram.instagram.mapper.DocumentMapper.DOCUMENT_MAPPER;
import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final MediaService mediaService;
    private final SessionUser sessionUser;

    //    @Async
    public Document saveDocument(MultipartFile file) {
        return documentRepository.save(
                Document.childBuilder()
                        .originalName(file.getOriginalFilename())
                        .generatedName(randomUUID() + file.getOriginalFilename())
                        .extension(StringUtils.getFilenameExtension(file.getOriginalFilename()))
                        .mimeType(file.getContentType())
                        .size(file.getSize())
                        .path(mediaService.upload(file))
                        .build()
        );
    }


    public List<Document> saveDocuments(List<MultipartFile> files) {
        if (sessionUser.id() == -1) {
            throw new RuntimeException("User not found");
        }
        if (files.isEmpty()) {
            throw new RuntimeException("File not found");
        }
        List<Document> documents = new ArrayList<>();
        files.forEach(
                file -> {
                     Document document = documentRepository.save(
                            Document.childBuilder()
                                    .createdBy(sessionUser.id())
                                    .originalName(file.getOriginalFilename())
                                    .generatedName(randomUUID() + file.getOriginalFilename())
                                    .extension(StringUtils.getFilenameExtension(file.getOriginalFilename()))
                                    .mimeType(file.getContentType())
                                    .size(file.getSize())
                                    .path(mediaService.upload(file))
                                    .build()
                    );
                     documents.add(document);
                }
        );
        return documents;
    }


    public Optional<Document> getDocument(Long id) {
        return documentRepository.findById(id);
    }

    public Page<Document> getDocsWithPagination(Pageable pageable) {
        return documentRepository.findAll(pageable);
    }


    public Document download(String fileName) {
        return documentRepository.findByName(fileName).orElseThrow(
                () -> new RuntimeException("Document not found")
        );
    }

    public Page<Document> getAllDocsBySessionUser(Pageable pageable) {
        if (sessionUser.id() == -1) {
            throw new RuntimeException("User not found");
        }
        return documentRepository.findAllByCreatedBy(sessionUser.id(), pageable);
    }


    public Document file(String filePath) {
        return documentRepository.findByPath(filePath).orElseThrow(
                () -> new RuntimeException("Document not found")
        );
    }

    public ReturnDocumentDTO downloadFileURI(String fileName) {
        Document doc = documentRepository.findByNameLink(fileName);
        ReturnDocumentDTO returnDocumentDTO = new ReturnDocumentDTO();
        BeanUtils.copyProperties(doc, returnDocumentDTO);
        return returnDocumentDTO;
    }
}

