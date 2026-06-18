package com.universite.unchk.service;

import com.universite.unchk.entity.Etudiant;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.EtudiantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EtudiantService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    public List<Etudiant> getAll() {
        return etudiantRepository.findAll();
    }

    public Etudiant getById(Long id) {
        return etudiantRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant non trouvé"));
    }

    public Etudiant create(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }
    public Etudiant findByUsername(String username) {
        return etudiantRepository.findByUserUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("Étudiant non trouvé pour l'utilisateur " + username));
    }

    public Etudiant update(Long id, Etudiant etudiantDetails) {
        Etudiant etudiant = getById(id);
        etudiant.setIne(etudiantDetails.getIne());
        etudiant.setNom(etudiantDetails.getNom());
        etudiant.setPrenom(etudiantDetails.getPrenom());
        etudiant.setDateNaissance(etudiantDetails.getDateNaissance());
        etudiant.setFormation(etudiantDetails.getFormation());
        etudiant.setPromo(etudiantDetails.getPromo());
        etudiant.setAnneeDebut(etudiantDetails.getAnneeDebut());
        etudiant.setAnneeSortie(etudiantDetails.getAnneeSortie());
        etudiant.setDiplomes(etudiantDetails.getDiplomes());
        etudiant.setAutresFormations(etudiantDetails.getAutresFormations());
        return etudiantRepository.save(etudiant);
    }

    public void delete(Long id) {
        etudiantRepository.deleteById(id);
    }
}