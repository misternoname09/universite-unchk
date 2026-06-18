package com.universite.unchk.service;

import com.universite.unchk.entity.Formation;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.FormationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FormationService {

    @Autowired
    private FormationRepository formationRepository;

    public List<Formation> getAll() {
        return formationRepository.findAll();
    }

    public Formation getById(Long id) {
        return formationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Formation non trouvée"));
    }

    public Formation create(Formation formation) {
        return formationRepository.save(formation);
    }

    public Formation update(Long id, Formation formationDetails) {
        Formation formation = getById(id);
        formation.setDateDebut(formationDetails.getDateDebut());
        formation.setDateFin(formationDetails.getDateFin());
        formation.setTypeFormation(formationDetails.getTypeFormation());
        formation.setNiveau(formationDetails.getNiveau());
        formation.setMontant(formationDetails.getMontant());
        formation.setTypeFinancement(formationDetails.getTypeFinancement());
        formation.setNbHommes(formationDetails.getNbHommes());
        formation.setNbFemmes(formationDetails.getNbFemmes());
        return formationRepository.save(formation);
    }

    public void delete(Long id) {
        formationRepository.deleteById(id);
    }
}