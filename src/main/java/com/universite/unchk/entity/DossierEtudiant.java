package com.universite.unchk.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "dossiers_etudiants")
public class DossierEtudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String cvUrl;
    private String relevesNotes;
    private String attestations;
    private String autresDocuments;

    @OneToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    public DossierEtudiant() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCvUrl() { return cvUrl; }
    public void setCvUrl(String cvUrl) { this.cvUrl = cvUrl; }
    public String getRelevesNotes() { return relevesNotes; }
    public void setRelevesNotes(String relevesNotes) { this.relevesNotes = relevesNotes; }
    public String getAttestations() { return attestations; }
    public void setAttestations(String attestations) { this.attestations = attestations; }
    public String getAutresDocuments() { return autresDocuments; }
    public void setAutresDocuments(String autresDocuments) { this.autresDocuments = autresDocuments; }
    public Etudiant getEtudiant() { return etudiant; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }
}