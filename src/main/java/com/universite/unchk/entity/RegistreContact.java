package com.universite.unchk.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "registre_contacts")
public class RegistreContact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateContact;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "etudiant_id")
    private Etudiant etudiant;

    public RegistreContact() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public LocalDate getDateContact() { return dateContact; }
    public void setDateContact(LocalDate dateContact) { this.dateContact = dateContact; }
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    public Etudiant getEtudiant() { return etudiant; }
    public void setEtudiant(Etudiant etudiant) { this.etudiant = etudiant; }
}