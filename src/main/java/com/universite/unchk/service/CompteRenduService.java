package com.universite.unchk.service;

import com.universite.unchk.entity.CompteRendu;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.CompteRenduRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompteRenduService {

    @Autowired
    private CompteRenduRepository repository;

    public List<CompteRendu> getAll() {
        return repository.findAll();
    }

    public CompteRendu getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Compte rendu non trouvé"));
    }

    public CompteRendu create(CompteRendu compteRendu) {
        compteRendu.setDateCreation(java.time.LocalDateTime.now());
        return repository.save(compteRendu);
    }

    public CompteRendu update(Long id, CompteRendu details) {
        CompteRendu existing = getById(id);
        existing.setTitre(details.getTitre());
        existing.setContenu(details.getContenu());
        existing.setType(details.getType());
        existing.setAuteur(details.getAuteur());
        existing.setDocumentJoint(details.getDocumentJoint());
        existing.setRolesAutorises(details.getRolesAutorises());
        return repository.save(existing);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Filtrage par rôle de l'utilisateur connecté
    public List<CompteRendu> getAllForCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentRole = auth.getAuthorities().iterator().next().getAuthority();
        return repository.findAll().stream()
                .filter(cr -> cr.getRolesAutorises().isEmpty() || cr.getRolesAutorises().contains(currentRole))
                .collect(Collectors.toList());
    }



}