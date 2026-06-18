package com.universite.unchk.controller;

import com.universite.unchk.entity.DossierEtudiant;
import com.universite.unchk.service.DossierEtudiantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/dossiers-etudiants")
public class DossierEtudiantController {
    @Autowired private DossierEtudiantService service;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public List<DossierEtudiant> getAll() { return service.getAll(); }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DossierEtudiant> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DossierEtudiant> create(@RequestBody DossierEtudiant dossier) {
        return new ResponseEntity<>(service.create(dossier), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DossierEtudiant> update(@PathVariable Long id, @RequestBody DossierEtudiant dossier) {
        return ResponseEntity.ok(service.update(id, dossier));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}