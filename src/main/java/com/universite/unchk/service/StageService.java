package com.universite.unchk.service;

import com.universite.unchk.entity.Stage;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.StageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StageService {

    @Autowired
    private StageRepository stageRepository;

    public List<Stage> getAll() {
        return stageRepository.findAll();
    }

    public Stage getById(Long id) {
        return stageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Stage non trouvé"));
    }

    public Stage create(Stage stage) {
        return stageRepository.save(stage);
    }

    public Stage update(Long id, Stage stageDetails) {
        Stage stage = getById(id);
        stage.setEntreprise(stageDetails.getEntreprise());
        stage.setDateDebut(stageDetails.getDateDebut());
        stage.setDateFin(stageDetails.getDateFin());
        stage.setBilan(stageDetails.getBilan());
        stage.setTuteur(stageDetails.getTuteur());
        return stageRepository.save(stage);
    }

    public void delete(Long id) {
        stageRepository.deleteById(id);
    }
}