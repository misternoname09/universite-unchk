package com.universite.unchk.repository;

import com.universite.unchk.entity.Reunion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReunionRepository extends JpaRepository<Reunion, Long> {
    List<Reunion> findByType(String type);
}