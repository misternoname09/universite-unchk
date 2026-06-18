package com.universite.unchk.repository;

import com.universite.unchk.entity.DossierEtudiant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DossierEtudiantRepository extends JpaRepository<DossierEtudiant, Long> {
}