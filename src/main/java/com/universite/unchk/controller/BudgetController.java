package com.universite.unchk.controller;

import com.universite.unchk.entity.Budget;
import com.universite.unchk.service.BudgetService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/budgets")
public class BudgetController {

    @Autowired
    private BudgetService budgetService;

    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINISTRATIF')")
    public List<Budget> getAll() {
        return budgetService.getAll();
    }

    @GetMapping("/stats/par-annee")
    public Map<Integer, BigDecimal> getBudgetParAnnee() {
        List<Budget> budgets = budgetService.getAll();
        return budgets.stream()
                .collect(Collectors.groupingBy(Budget::getAnnee,
                        Collectors.mapping(Budget::getMontant,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'ADMINISTRATIF')")
    public ResponseEntity<Budget> getById(@PathVariable Long id) {
        return ResponseEntity.ok(budgetService.getById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Budget> create(@Valid @RequestBody Budget budget) {
        return new ResponseEntity<>(budgetService.create(budget), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Budget> update(@PathVariable Long id, @Valid @RequestBody Budget budget) {
        return ResponseEntity.ok(budgetService.update(id, budget));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        budgetService.delete(id);
        return ResponseEntity.noContent().build();
    }


}