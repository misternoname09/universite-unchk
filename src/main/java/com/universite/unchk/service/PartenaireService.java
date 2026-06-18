package com.universite.unchk.service;

import com.universite.unchk.entity.Partenaire;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.PartenaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PartenaireService {

    @Autowired
    private PartenaireRepository partenaireRepository;

    public List<Partenaire> getAll() {
        return partenaireRepository.findAll();
    }

    public Partenaire getById(Long id) {
        return partenaireRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Partenaire non trouvé"));
    }

    public Partenaire create(Partenaire partenaire) {
        return partenaireRepository.save(partenaire);
    }

    public Partenaire update(Long id, Partenaire partenaireDetails) {
        Partenaire partenaire = getById(id);
        partenaire.setNom(partenaireDetails.getNom());
        partenaire.setSecteur(partenaireDetails.getSecteur());
        partenaire.setTypePartenariat(partenaireDetails.getTypePartenariat());
        partenaire.setContact(partenaireDetails.getContact());
        return partenaireRepository.save(partenaire);
    }

    public void delete(Long id) {
        partenaireRepository.deleteById(id);
    }
}