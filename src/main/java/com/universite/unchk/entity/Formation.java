package com.universite.unchk.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "formations")
public class Formation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String typeFormation;
    private String niveau;
    private BigDecimal montant;
    private String typeFinancement;
    private Integer nbHommes;
    private Integer nbFemmes;

    // Constructeur par défaut (obligatoire JPA)
    public Formation() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public String getTypeFormation() { return typeFormation; }
    public void setTypeFormation(String typeFormation) { this.typeFormation = typeFormation; }

    public String getNiveau() { return niveau; }
    public void setNiveau(String niveau) { this.niveau = niveau; }

    public BigDecimal getMontant() { return montant; }
    public void setMontant(BigDecimal montant) { this.montant = montant; }

    public String getTypeFinancement() { return typeFinancement; }
    public void setTypeFinancement(String typeFinancement) { this.typeFinancement = typeFinancement; }

    public Integer getNbHommes() { return nbHommes; }
    public void setNbHommes(Integer nbHommes) { this.nbHommes = nbHommes; }

    public Integer getNbFemmes() { return nbFemmes; }
    public void setNbFemmes(Integer nbFemmes) { this.nbFemmes = nbFemmes; }
}