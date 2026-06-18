package com.universite.unchk.service;

import com.universite.unchk.entity.Formateur;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.FormateurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FormateurService {
    @Autowired
    private FormateurRepository formateurRepository;

    public List<Formateur> getAll() {
        return formateurRepository.findAll();
    }

    public Formateur getById(Long id) {
        return formateurRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Formateur non trouvé"));
    }

    public Formateur create(Formateur formateur) {
        return formateurRepository.save(formateur);
    }

    public Formateur update(Long id, Formateur formateurDetails) {
        Formateur formateur = getById(id);
        formateur.setNom(formateurDetails.getNom());
        formateur.setPrenom(formateurDetails.getPrenom());
        formateur.setEmail(formateurDetails.getEmail());
        formateur.setType(formateurDetails.getType());
        return formateurRepository.save(formateur);
    }

    public void delete(Long id) {
        formateurRepository.deleteById(id);
    }
}