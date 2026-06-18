package com.universite.unchk.service;

import com.universite.unchk.entity.ActivitePedagogique;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.ActivitePedagogiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ActivitePedagogiqueService {
    @Autowired
    private ActivitePedagogiqueRepository repository;

    public List<ActivitePedagogique> getAll() {
        return repository.findAll();
    }

    public ActivitePedagogique getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Activité non trouvée"));
    }

    public ActivitePedagogique create(ActivitePedagogique activite) {
        return repository.save(activite);
    }

    public ActivitePedagogique update(Long id, ActivitePedagogique details) {
        ActivitePedagogique activite = getById(id);
        activite.setTitre(details.getTitre());
        activite.setType(details.getType());
        activite.setMatiere(details.getMatiere());
        activite.setDateDebut(details.getDateDebut());
        activite.setDateFin(details.getDateFin());
        activite.setDescription(details.getDescription());
        activite.setLienRessource(details.getLienRessource());
        activite.setFormation(details.getFormation());
        activite.setFormateur(details.getFormateur());
        return repository.save(activite);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}