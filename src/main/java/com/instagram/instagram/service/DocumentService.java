package com.instagram.instagram.service;

import com.instagram.instagram.config.security.SessionUser;
import com.instagram.instagram.domains.Saved;
import com.instagram.instagram.domains.auth.AuthUser;
import com.instagram.instagram.domains.basic.Document;
import com.instagram.instagram.firebase.MediaService;
import com.instagram.instagram.repository.DocumentRepository;
import com.instagram.instagram.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final MediaService mediaService;
    private final SessionUser sessionUser;
    private final UserRepository userRepository;
//    private final UserService userService;

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
        return documentRepository.findByPath(fileName).orElseThrow(
                () -> new RuntimeException("Document not found")
        );
    }

    public Page<Document> getAllDocsBySessionUser(Pageable pageable) {
        return documentRepository.findAllByCreatedBy(sessionUser.id(), pageable);
    }

    public List<String> sa(){
        return List.of();
    }
}

