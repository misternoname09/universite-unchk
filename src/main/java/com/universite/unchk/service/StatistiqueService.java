package com.universite.unchk.service;

import com.universite.unchk.entity.StatistiqueInsertion;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.StatistiqueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class StatistiqueService {

    @Autowired
    private StatistiqueRepository statistiqueRepository;

    public List<StatistiqueInsertion> getAll() {
        return statistiqueRepository.findAll();
    }

    public StatistiqueInsertion getById(Long id) {
        return statistiqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Statistique non trouvée"));
    }

    public StatistiqueInsertion create(StatistiqueInsertion stat) {
        return statistiqueRepository.save(stat);
    }

    public StatistiqueInsertion update(Long id, StatistiqueInsertion statDetails) {
        StatistiqueInsertion stat = getById(id);
        stat.setAnnee(statDetails.getAnnee());
        stat.setNbAutoEmploi(statDetails.getNbAutoEmploi());
        stat.setNbSalarie(statDetails.getNbSalarie());
        return statistiqueRepository.save(stat);
    }

    public void delete(Long id) {
        statistiqueRepository.deleteById(id);
    }
}