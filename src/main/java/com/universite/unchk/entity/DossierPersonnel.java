package com.universite.unchk.entity;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "dossiers_personnels")
public class DossierPersonnel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String poste;
    private String diplomes;
    private BigDecimal salaire;
    private String cvUrl;
    private Long userId;  // simple champ, pas de relation

    public DossierPersonnel() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPoste() { return poste; }
    public void setPoste(String poste) { this.poste = poste; }
    public String getDiplomes() { return diplomes; }
    public void setDiplomes(String diplomes) { this.diplomes = diplomes; }
    public BigDecimal getSalaire() { return salaire; }
    public void setSalaire(BigDecimal salaire) { this.salaire = salaire; }
    public String getCvUrl() { return cvUrl; }
    public void setCvUrl(String cvUrl) { this.cvUrl = cvUrl; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
}