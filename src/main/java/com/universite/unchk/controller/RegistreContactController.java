package com.universite.unchk.controller;

import com.universite.unchk.entity.RegistreContact;
import com.universite.unchk.service.RegistreContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/registre-contacts")
public class RegistreContactController {

    @Autowired
    private RegistreContactService registreService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'APPUI_INSERTION')")
    public List<RegistreContact> getAll() {
        return registreService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'APPUI_INSERTION')")
    public ResponseEntity<RegistreContact> getById(@PathVariable Long id) {
        return ResponseEntity.ok(registreService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegistreContact> create(@Valid @RequestBody RegistreContact contact) {
        return new ResponseEntity<>(registreService.create(contact), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RegistreContact> update(@PathVariable Long id, @Valid @RequestBody RegistreContact contact) {
        return ResponseEntity.ok(registreService.update(id, contact));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        registreService.delete(id);
        return ResponseEntity.noContent().build();
    }
}