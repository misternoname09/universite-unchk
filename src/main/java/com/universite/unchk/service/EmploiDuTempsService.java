package com.universite.unchk.service;

import com.universite.unchk.entity.EmploiDuTemps;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.EmploiDuTempsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EmploiDuTempsService {
    @Autowired
    private EmploiDuTempsRepository emploiDuTempsRepository;

    public List<EmploiDuTemps> getAll() {
        return emploiDuTempsRepository.findAll();
    }

    public EmploiDuTemps getById(Long id) {
        return emploiDuTempsRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Emploi du temps non trouvé"));
    }

    public EmploiDuTemps create(EmploiDuTemps emploi) {
        return emploiDuTempsRepository.save(emploi);
    }

    public EmploiDuTemps update(Long id, EmploiDuTemps emploiDetails) {
        EmploiDuTemps emploi = getById(id);
        emploi.setJour(emploiDetails.getJour());
        emploi.setHeureDebut(emploiDetails.getHeureDebut());
        emploi.setHeureFin(emploiDetails.getHeureFin());
        emploi.setSalle(emploiDetails.getSalle());
        emploi.setMatiere(emploiDetails.getMatiere());
        emploi.setFormation(emploiDetails.getFormation());
        emploi.setFormateur(emploiDetails.getFormateur());
        return emploiDuTempsRepository.save(emploi);
    }

    public void delete(Long id) {
        emploiDuTempsRepository.deleteById(id);
    }
}