package com.universite.unchk.controller;

import com.universite.unchk.entity.DossierPersonnel;
import com.universite.unchk.service.DossierPersonnelService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/dossiers-personnels")
public class DossierPersonnelController {

    @Autowired
    private DossierPersonnelService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<DossierPersonnel> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DossierPersonnel> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DossierPersonnel> create(@Valid @RequestBody DossierPersonnel dossier) {
        return new ResponseEntity<>(service.create(dossier), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DossierPersonnel> update(@PathVariable Long id, @Valid @RequestBody DossierPersonnel dossier) {
        return ResponseEntity.ok(service.update(id, dossier));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}