package com.universite.unchk.controller;

import com.universite.unchk.entity.EmploiDuTemps;
import com.universite.unchk.service.EmploiDuTempsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/emplois-temps")
public class EmploiDuTempsController {

    @Autowired
    private EmploiDuTempsService emploiService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public List<EmploiDuTemps> getAll() {
        return emploiService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public ResponseEntity<EmploiDuTemps> getById(@PathVariable Long id) {
        return ResponseEntity.ok(emploiService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmploiDuTemps> create(@Valid @RequestBody EmploiDuTemps emploi) {
        return new ResponseEntity<>(emploiService.create(emploi), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmploiDuTemps> update(@PathVariable Long id, @Valid @RequestBody EmploiDuTemps emploi) {
        return ResponseEntity.ok(emploiService.update(id, emploi));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        emploiService.delete(id);
        return ResponseEntity.noContent().build();
    }
}