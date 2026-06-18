package com.universite.unchk.service;

import com.universite.unchk.entity.Document;
import com.universite.unchk.exception.ResourceNotFoundException;
import com.universite.unchk.repository.DocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DocumentService {
    @Autowired
    private DocumentRepository documentRepository;

    private final String uploadDir = "uploads";

    public List<Document> getAll() {
        return documentRepository.findAll();
    }

    public Document getById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Document non trouvé"));
    }

    public Document save(Document document, MultipartFile file) throws IOException {
        Path uploadPath = Paths.get(uploadDir);
        if (!Files.exists(uploadPath)) Files.createDirectories(uploadPath);
        String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = uploadPath.resolve(filename);
        Files.copy(file.getInputStream(), filePath);
        document.setCheminFichier(filePath.toString());
        document.setDateCreation(LocalDateTime.now());
        return documentRepository.save(document);
    }

    public Document update(Long id, Document documentDetails, MultipartFile file) throws IOException {
        Document existing = getById(id);
        existing.setTitre(documentDetails.getTitre());
        existing.setType(documentDetails.getType());
        existing.setDescription(documentDetails.getDescription());
        existing.setAuteur(documentDetails.getAuteur());
        existing.setRolesAutorises(documentDetails.getRolesAutorises());

        if (file != null && !file.isEmpty()) {
            Path oldPath = Paths.get(existing.getCheminFichier());
            if (Files.exists(oldPath)) Files.delete(oldPath);
            Path uploadPath = Paths.get(uploadDir);
            String filename = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
            Path filePath = uploadPath.resolve(filename);
            Files.copy(file.getInputStream(), filePath);
            existing.setCheminFichier(filePath.toString());
        }
        return documentRepository.save(existing);
    }

    public void delete(Long id) throws IOException {
        Document doc = getById(id);
        Path filePath = Paths.get(doc.getCheminFichier());
        if (Files.exists(filePath)) Files.delete(filePath);
        documentRepository.deleteById(id);
    }

    public List<Document> getAllForCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String currentRole = auth.getAuthorities().iterator().next().getAuthority();
        return documentRepository.findAll().stream()
                .filter(doc -> doc.getRolesAutorises().isEmpty() || doc.getRolesAutorises().contains(currentRole))
                .collect(Collectors.toList());
    }
}