package com.universite.unchk.controller;

import com.universite.unchk.entity.CompteRendu;
import com.universite.unchk.service.CompteRenduService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/comptes-rendus")
public class CompteRenduController {

    @Autowired
    private CompteRenduService compteRenduService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT', 'ETUDIANT')")
    public List<CompteRendu> getAll() {
        return compteRenduService.getAllForCurrentUser();
    }



    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT', 'ADMINISTRATIF')")
    public ResponseEntity<CompteRendu> getById(@PathVariable Long id) {
        return ResponseEntity.ok(compteRenduService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public ResponseEntity<CompteRendu> create(@Valid @RequestBody CompteRendu compteRendu) {
        return new ResponseEntity<>(compteRenduService.create(compteRendu), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public ResponseEntity<CompteRendu> update(@PathVariable Long id, @Valid @RequestBody CompteRendu compteRendu) {
        return ResponseEntity.ok(compteRenduService.update(id, compteRendu));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        compteRenduService.delete(id);
        return ResponseEntity.noContent().build();
    }
}