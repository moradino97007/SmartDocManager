package com.smartdocmanager.infrastructure.adapter.in.web;


import com.smartdocmanager.domain.model.Document;
import com.smartdocmanager.domain.sevice.DocumentService;
import com.smartdocmanager.infrastructure.adapter.in.web.dto.DocumentRequest;
import com.smartdocmanager.infrastructure.adapter.in.web.dto.DocumentResponse ;
import com.smartdocmanager.infrastructure.adapter.out.persistance.DocumentEntity;
import com.smartdocmanager.infrastructure.adapter.out.persistance.DocumentJpaRepository;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/documents")
public class DocumentController {



    private final DocumentService documentService;
    // Injection de dependence par constructeur
    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping
    public ResponseEntity<List<DocumentResponse>> getAllDocuments() {
        List<DocumentResponse> response = documentService.findAll().stream().map(this::toResponse).toList() ;
        return ResponseEntity.ok(response);
    }


    @GetMapping("/{idDocument}")
    public ResponseEntity<DocumentResponse> getDocumentById(@PathVariable String idDocument) {
        DocumentResponse response = toResponse(documentService.findById(idDocument));
        return ResponseEntity.ok(response);
    }


    @PostMapping
    public ResponseEntity<DocumentResponse> postDocument(@Valid @RequestBody DocumentRequest request) {
        DocumentResponse response = toResponse(documentService.createDocument(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }



    private DocumentResponse toResponse(Document document) {
        return new DocumentResponse(
                document.getId(),
                document.getTitle(),
                document.getAuthor(),
                document.getContent() != null && document.getContent().length() > 200, // règle métier
                document.getCreatedAt()
        );
    }


}
