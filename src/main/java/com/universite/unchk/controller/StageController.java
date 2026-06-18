package com.universite.unchk.controller;

import com.universite.unchk.entity.Stage;
import com.universite.unchk.service.StageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/stages")
public class StageController {

    @Autowired
    private StageService stageService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'APPUI_INSERTION')")
    public List<Stage> getAll() {
        return stageService.getAll();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'APPUI_INSERTION')")
    public ResponseEntity<Stage> getById(@PathVariable Long id) {
        return ResponseEntity.ok(stageService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Stage> create(@Valid @RequestBody Stage stage) {
        return new ResponseEntity<>(stageService.create(stage), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Stage> update(@PathVariable Long id, @Valid @RequestBody Stage stage) {
        return ResponseEntity.ok(stageService.update(id, stage));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        stageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}