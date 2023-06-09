package com.instagram.instagram.controller;

import com.instagram.instagram.config.security.SessionUser;
import com.instagram.instagram.domains.Link;
import com.instagram.instagram.domains.basic.Document;
import com.instagram.instagram.domains.basic.Post;
import com.instagram.instagram.dto.ReturnDocumentDTO;
import com.instagram.instagram.service.DocumentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/document")
@Tag(
        name = "Media Controller",
        description = "This is a Java class named \"DocumentController\" that serves as a controller for a REST API. It contains various HTTP endpoints that allow clients to interact with the API, including endpoints for retrieving, creating, and uploading documents."
)
public class DocumentController {

    private final DocumentService documentService;

//    @Operation(summary = "This API used for creating a post",
//            description = "This endpoint was designed for creating a post"
//            /*,deprecated = true*/)
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Post Successfully Created",
//                    content = {
//                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
//                                    schema = @Schema(implementation = Post.class)
//                            )
//                    }),
//            @ApiResponse(responseCode = "500", description = "Internal Server Error",
//                    content = {
//                            @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
//                                    schema = @Schema(implementation = RuntimeException.class)
//                            )
//                    })
//    })
    @GetMapping("/{id}")
    public ResponseEntity<Document> getDocument(@PathVariable Long id){
        return ResponseEntity.ok(
                documentService.getDocument(id).orElseThrow(
                        () -> new RuntimeException("Document not found")
                )
        );
    }

    @GetMapping("/createdBy")
    public Page<Document> getAllDocsBySessionUser(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page
    ){
        Pageable pageable = PageRequest.of(page, size);
        return documentService.getAllDocsBySessionUser(pageable);
    }


    @GetMapping("/pagination")
    public Page<Document> getPosts(
            @RequestParam(value = "size", defaultValue = "10") int size,
            @RequestParam(value = "page", defaultValue = "0") int page
    ){
        Pageable pageable = PageRequest.of(page, size);
        return documentService.getDocsWithPagination(pageable);
    }

    @PostMapping(name = "/uploadFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<List<Document>> uploadDocs(@RequestPart("files") List<MultipartFile> files){
        return ResponseEntity.ok(
                documentService.saveDocuments(files)
        );
    }

    @PostMapping("filename")
    public ResponseEntity<Document> downloadFile(String fileName) throws IOException {
        return ResponseEntity.ok(
                documentService.download(fileName)
        );
    }
    @GetMapping("filename")
    public ResponseEntity<ReturnDocumentDTO> downloadFileURI(String fileName) throws IOException, URISyntaxException {
        return ResponseEntity.ok(
                documentService.downloadFileURI(fileName)
        );
    }
    @PostMapping("/filepath")
    public ResponseEntity<Document> file(String filePath) throws IOException {
        return ResponseEntity.ok(
                documentService.file(filePath)
        );
    }


//    @PostMapping(name = "/uploadFile")
//    public ResponseEntity<Document> uploadDoc(@RequestParam("file") MultipartFile file){
//        return ResponseEntity.ok(
//                documentService.saveDocument(file)
//        );
//    }

//    @PostMapping("/profile/pic/{fileName}")
//    public Object download(@PathVariable String fileName) {
////        logger.info("HIT -/download | File Name : {}", fileName);
//        return documentService.download(fileName);
//    }
}

