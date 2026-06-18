package com.universite.unchk.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "statistiques_insertion")
public class StatistiqueInsertion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer annee;
    private Integer nbAutoEmploi;
    private Integer nbSalarie;

    public StatistiqueInsertion() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Integer getAnnee() { return annee; }
    public void setAnnee(Integer annee) { this.annee = annee; }
    public Integer getNbAutoEmploi() { return nbAutoEmploi; }
    public void setNbAutoEmploi(Integer nbAutoEmploi) { this.nbAutoEmploi = nbAutoEmploi; }
    public Integer getNbSalarie() { return nbSalarie; }
    public void setNbSalarie(Integer nbSalarie) { this.nbSalarie = nbSalarie; }
}