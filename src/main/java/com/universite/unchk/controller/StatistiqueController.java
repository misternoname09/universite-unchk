package com.universite.unchk.controller;

import com.universite.unchk.entity.StatistiqueInsertion;
import com.universite.unchk.repository.EtudiantRepository;
import com.universite.unchk.service.StatistiqueService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/statistiques")
public class StatistiqueController {

    @Autowired
    private StatistiqueService statistiqueService;

    @Autowired
    private EtudiantRepository etudiantRepository;

    // Récupérer toutes les statistiques
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'APPUI_INSERTION')")
    public List<StatistiqueInsertion> getAll() {
        return statistiqueService.getAll();
    }

    // Récupérer une statistique par ID
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'APPUI_INSERTION')")
    public ResponseEntity<StatistiqueInsertion> getById(@PathVariable Long id) {
        return ResponseEntity.ok(statistiqueService.getById(id));
    }

    // Créer une nouvelle statistique
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StatistiqueInsertion> create(@Valid @RequestBody StatistiqueInsertion stat) {
        return new ResponseEntity<>(statistiqueService.create(stat), HttpStatus.CREATED);
    }

    // Mettre à jour une statistique existante
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<StatistiqueInsertion> update(@PathVariable Long id, @Valid @RequestBody StatistiqueInsertion stat) {
        return ResponseEntity.ok(statistiqueService.update(id, stat));
    }

    // Supprimer une statistique
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        statistiqueService.delete(id);
        return ResponseEntity.noContent().build();
    }

    // Statistiques : nombre d'étudiants par formation
    @GetMapping("/etudiants-par-formation")
    public Map<String, Long> getEtudiantsParFormation() {
        List<Object[]> results = etudiantRepository.countByFormation();
        return results.stream().collect(Collectors.toMap(
                r -> (String) r[0],
                r -> (Long) r[1]
        ));
    }
}
