package com.universite.unchk.repository;

import com.universite.unchk.entity.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {

    // Recherche par formation
    List<Etudiant> findByFormation(String formation);

    // Compter le nombre d'étudiants par formation
    @Query("SELECT e.formation, COUNT(e) FROM Etudiant e GROUP BY e.formation")
    List<Object[]> countByFormation();

    // Recherche par username lié à un User
    Optional<Etudiant> findByUserUsername(String username);
}
