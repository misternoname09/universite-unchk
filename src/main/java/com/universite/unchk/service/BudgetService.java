package com.universite.unchk.service;

import com.universite.unchk.entity.Budget;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.BudgetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class BudgetService {

    @Autowired
    private BudgetRepository budgetRepository;

    public List<Budget> getAll() {
        return budgetRepository.findAll();
    }

    public Budget getById(Long id) {
        return budgetRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Budget non trouvé"));
    }

    public Budget create(Budget budget) {
        budget.setDateCreation(LocalDate.now());
        return budgetRepository.save(budget);
    }

    public Budget update(Long id, Budget budgetDetails) {
        Budget existing = getById(id);
        existing.setType(budgetDetails.getType());
        existing.setDescription(budgetDetails.getDescription());
        existing.setMontant(budgetDetails.getMontant());
        existing.setAnnee(budgetDetails.getAnnee());
        return budgetRepository.save(existing);
    }

    public void delete(Long id) {
        budgetRepository.deleteById(id);
    }
}