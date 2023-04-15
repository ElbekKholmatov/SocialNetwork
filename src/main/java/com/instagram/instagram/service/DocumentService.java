package com.instagram.instagram.service;

import com.instagram.instagram.domains.basic.Document;
import com.instagram.instagram.firebase.MediaService;
import com.instagram.instagram.repository.DocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.UUID.randomUUID;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final MediaService mediaService;


    public Document saveDocument(MultipartFile file) {
        return documentRepository.save(
                Document.childBuilder()
                        .originalName(file.getOriginalFilename())
                        .generatedName(randomUUID()+file.getOriginalFilename())
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

    public List<Document> getDocuments() {
        return documentRepository.findAll();
    }

    public Optional<Document> getDocument(Long id) {
        return documentRepository.findById(id);
    }

    public Page<Document> getDocsWithPagination(Pageable pageable) {
        return documentRepository.findAll(pageable);
    }



}

