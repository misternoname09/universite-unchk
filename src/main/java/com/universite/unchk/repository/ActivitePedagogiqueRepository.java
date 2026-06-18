package com.universite.unchk.repository;

import com.universite.unchk.entity.ActivitePedagogique;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ActivitePedagogiqueRepository extends JpaRepository<ActivitePedagogique, Long> {
    List<ActivitePedagogique> findByType(String type);
    List<ActivitePedagogique> findByFormationId(Long formationId);
}