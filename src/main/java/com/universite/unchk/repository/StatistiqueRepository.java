package com.universite.unchk.repository;

import com.universite.unchk.entity.StatistiqueInsertion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatistiqueRepository extends JpaRepository<StatistiqueInsertion, Long> {
}