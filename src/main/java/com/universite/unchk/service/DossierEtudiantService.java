package com.universite.unchk.service;

import com.universite.unchk.entity.DossierEtudiant;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.DossierEtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DossierEtudiantService {
    @Autowired
    private DossierEtudiantRepository repository;

    public List<DossierEtudiant> getAll() { return repository.findAll(); }
    public DossierEtudiant getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Dossier étudiant non trouvé"));
    }
    public DossierEtudiant create(DossierEtudiant dossier) { return repository.save(dossier); }
    public DossierEtudiant update(Long id, DossierEtudiant details) {
        DossierEtudiant d = getById(id);
        d.setCvUrl(details.getCvUrl());
        d.setRelevesNotes(details.getRelevesNotes());
        d.setAttestations(details.getAttestations());
        d.setAutresDocuments(details.getAutresDocuments());
        d.setEtudiant(details.getEtudiant());
        return repository.save(d);
    }
    public void delete(Long id) { repository.deleteById(id); }
}