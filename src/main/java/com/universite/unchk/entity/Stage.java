package com.universite.unchk.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "stages")
public class Stage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String entreprise;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String bilan;
    private String tuteur;

    // Constructeurs
    public Stage() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEntreprise() { return entreprise; }
    public void setEntreprise(String entreprise) { this.entreprise = entreprise; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public String getBilan() { return bilan; }
    public void setBilan(String bilan) { this.bilan = bilan; }

    public String getTuteur() { return tuteur; }
    public void setTuteur(String tuteur) { this.tuteur = tuteur; }
}