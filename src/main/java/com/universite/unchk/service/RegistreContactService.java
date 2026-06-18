package com.universite.unchk.service;

import com.universite.unchk.entity.RegistreContact;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.RegistreContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RegistreContactService {
    @Autowired
    private RegistreContactRepository registreRepository;

    public List<RegistreContact> getAll() {
        return registreRepository.findAll();
    }

    public RegistreContact getById(Long id) {
        return registreRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Contact non trouvé"));
    }

    public RegistreContact create(RegistreContact contact) {
        return registreRepository.save(contact);
    }

    public RegistreContact update(Long id, RegistreContact contactDetails) {
        RegistreContact contact = getById(id);
        contact.setDateContact(contactDetails.getDateContact());
        contact.setNotes(contactDetails.getNotes());
        contact.setEtudiant(contactDetails.getEtudiant());
        return registreRepository.save(contact);
    }

    public void delete(Long id) {
        registreRepository.deleteById(id);
    }
}