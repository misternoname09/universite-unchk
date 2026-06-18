package com.universite.unchk.repository;

import com.universite.unchk.entity.EmploiDuTemps;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface EmploiDuTempsRepository extends JpaRepository<EmploiDuTemps, Long> {
    List<EmploiDuTemps> findByFormationId(Long formationId);
}