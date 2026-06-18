package com.universite.unchk.controller;

import com.universite.unchk.entity.Partenaire;
import com.universite.unchk.service.PartenaireService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/partenaires")
public class PartenaireController {

    @Autowired
    private PartenaireService partenaireService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'APPUI_INSERTION')")
    public List<Partenaire> getAll() {
        return partenaireService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'APPUI_INSERTION')")
    public ResponseEntity<Partenaire> getById(@PathVariable Long id) {
        return ResponseEntity.ok(partenaireService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Partenaire> create(@Valid @RequestBody Partenaire partenaire) {
        return new ResponseEntity<>(partenaireService.create(partenaire), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Partenaire> update(@PathVariable Long id, @Valid @RequestBody Partenaire partenaire) {
        return ResponseEntity.ok(partenaireService.update(id, partenaire));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        partenaireService.delete(id);
        return ResponseEntity.noContent().build();
    }
}