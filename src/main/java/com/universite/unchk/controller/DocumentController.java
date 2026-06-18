package com.universite.unchk.controller;

import com.universite.unchk.entity.Document;
import com.universite.unchk.service.DocumentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public List<Document> getAll() {
        return documentService.getAllForCurrentUser(); // Filtrage par rôle
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public ResponseEntity<Document> getById(@PathVariable Long id) {
        return ResponseEntity.ok(documentService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Document> create(@RequestPart("document") @Valid Document document,
                                           @RequestPart("file") MultipartFile file) throws IOException {
        return new ResponseEntity<>(documentService.save(document, file), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Document> update(@PathVariable Long id,
                                           @RequestPart("document") @Valid Document document,
                                           @RequestPart(value = "file", required = false) MultipartFile file) throws IOException {
        return ResponseEntity.ok(documentService.update(id, document, file));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) throws IOException {
        documentService.delete(id);
        return ResponseEntity.noContent().build();
    }
}