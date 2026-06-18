package com.universite.unchk.controller;

import com.universite.unchk.entity.Formateur;
import com.universite.unchk.service.FormateurService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/formateurs")
public class FormateurController {

    @Autowired
    private FormateurService formateurService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public List<Formateur> getAll() {
        return formateurService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public ResponseEntity<Formateur> getById(@PathVariable Long id) {
        return ResponseEntity.ok(formateurService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Formateur> create(@Valid @RequestBody Formateur formateur) {
        return new ResponseEntity<>(formateurService.create(formateur), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Formateur> update(@PathVariable Long id, @Valid @RequestBody Formateur formateur) {
        return ResponseEntity.ok(formateurService.update(id, formateur));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        formateurService.delete(id);
        return ResponseEntity.noContent().build();
    }
}