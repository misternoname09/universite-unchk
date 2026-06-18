package com.universite.unchk.repository;

import com.universite.unchk.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    List<Budget> findByAnnee(Integer annee);
    List<Budget> findByType(String type);
}