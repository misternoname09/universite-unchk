package com.universite.unchk.repository;

import com.universite.unchk.entity.Role;
import com.universite.unchk.entity.enums.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}