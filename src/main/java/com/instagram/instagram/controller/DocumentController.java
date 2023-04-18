package com.instagram.instagram.controller;

import com.instagram.instagram.domains.basic.Document;
import com.instagram.instagram.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequiredArgsConstructor
@RequestMapping("/document")
public class DocumentController {

    private final DocumentService documentService;
    private final PagedResourcesAssembler<Document> pagedResourcesAssembler;

    @GetMapping("/")
    public ResponseEntity<List<Document>> getDocuments(){
        return ResponseEntity.ok(
                documentService.getDocuments()
        );
    }
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable Long id){
        return ResponseEntity.ok(
                documentService.getDocument(id).orElseThrow(
                        () -> new RuntimeException("Document not found")
                )
        );
    }

    @GetMapping("/pagination")
    public Page<Document> getPosts(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page
    ){
        Pageable pageable = PageRequest.of(page, size);
        return documentService.getDocsWithPagination(pageable);
    }

    @PostMapping(name = "/uploadFiles")
    public ResponseEntity<List<Document>> uploadDocs(@RequestPart("files") List<MultipartFile> files){
        return ResponseEntity.ok(
                documentService.saveDocuments(files)
        );
    }
    @PostMapping(name = "/uploadFile")
    public ResponseEntity<Document> uploadDoc(@RequestParam("file") MultipartFile file){
        return ResponseEntity.ok(
                documentService.saveDocument(file)
        );
    }

//    @PostMapping("/profile/pic/{fileName}")
//    public Object download(@PathVariable String fileName) {
////        logger.info("HIT -/download | File Name : {}", fileName);
//        return documentService.download(fileName);
//    }
}

