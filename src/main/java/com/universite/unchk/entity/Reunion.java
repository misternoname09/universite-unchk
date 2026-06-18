package com.universite.unchk.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reunions")
public class Reunion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String type; // SUIVI_TUTORAT, PREPARATION_COURS, PREPARATION_EVALUATION
    private LocalDateTime dateReunion;
    private String description;
    private String responsable;

    @ManyToOne
    @JoinColumn(name = "formation_id")
    private Formation formation;

    // Constructeurs
    public Reunion() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public LocalDateTime getDateReunion() { return dateReunion; }
    public void setDateReunion(LocalDateTime dateReunion) { this.dateReunion = dateReunion; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getResponsable() { return responsable; }
    public void setResponsable(String responsable) { this.responsable = responsable; }
    public Formation getFormation() { return formation; }
    public void setFormation(Formation formation) { this.formation = formation; }
}