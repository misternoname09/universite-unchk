package com.universite.unchk.service;

import com.universite.unchk.entity.DossierPersonnel;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.DossierPersonnelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DossierPersonnelService {
    @Autowired
    private DossierPersonnelRepository repository;

    public List<DossierPersonnel> getAll() {
        return repository.findAll();
    }

    public DossierPersonnel getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dossier non trouvé"));
    }

    public DossierPersonnel create(DossierPersonnel dossier) {
        if (dossier.getUserId() == null) dossier.setUserId(1L);
        return repository.save(dossier);
    }

    public DossierPersonnel update(Long id, DossierPersonnel details) {
        DossierPersonnel dossier = getById(id);
        dossier.setPoste(details.getPoste());
        dossier.setDiplomes(details.getDiplomes());
        dossier.setSalaire(details.getSalaire());
        dossier.setCvUrl(details.getCvUrl());
        dossier.setUserId(details.getUserId());
        return repository.save(dossier);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}