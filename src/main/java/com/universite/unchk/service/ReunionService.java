package com.universite.unchk.service;

import com.universite.unchk.entity.Reunion;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.ReunionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ReunionService {
    @Autowired
    private ReunionRepository repository;

    public List<Reunion> getAll() {
        return repository.findAll();
    }

    public Reunion getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Réunion non trouvée"));
    }

    public Reunion create(Reunion reunion) {
        return repository.save(reunion);
    }

    public Reunion update(Long id, Reunion details) {
        Reunion reunion = getById(id);
        reunion.setTitre(details.getTitre());
        reunion.setType(details.getType());
        reunion.setDateReunion(details.getDateReunion());
        reunion.setDescription(details.getDescription());
        reunion.setResponsable(details.getResponsable());
        reunion.setFormation(details.getFormation());
        return repository.save(reunion);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}