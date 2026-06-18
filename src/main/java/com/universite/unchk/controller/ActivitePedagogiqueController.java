package com.universite.unchk.controller;

import com.universite.unchk.entity.ActivitePedagogique;
import com.universite.unchk.service.ActivitePedagogiqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/activites-pedagogiques")
public class ActivitePedagogiqueController {

    @Autowired
    private ActivitePedagogiqueService service;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public List<ActivitePedagogique> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ENSEIGNANT')")
    public ResponseEntity<ActivitePedagogique> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ActivitePedagogique> create(@Valid @RequestBody ActivitePedagogique activite) {
        return new ResponseEntity<>(service.create(activite), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ActivitePedagogique> update(@PathVariable Long id, @Valid @RequestBody ActivitePedagogique activite) {
        return ResponseEntity.ok(service.update(id, activite));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}