package com.universite.unchk.repository;

import com.universite.unchk.entity.RegistreContact;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RegistreContactRepository extends JpaRepository<RegistreContact, Long> {
    List<RegistreContact> findByEtudiantId(Long etudiantId);
}