package com.universite.unchk.repository;

import com.universite.unchk.entity.CompteRendu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompteRenduRepository extends JpaRepository<CompteRendu, Long> {
}