package com.universite.unchk.entity;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "etudiants")
public class Etudiant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String ine;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String formation;
    private String promo;
    private Integer anneeDebut;
    private Integer anneeSortie;
    @ElementCollection
    private List<String> diplomes = new ArrayList<>();
    private String autresFormations;
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    // Getters et setters (générés manuellement)
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getIne() { return ine; }
    public void setIne(String ine) { this.ine = ine; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getPrenom() { return prenom; }
    public void setPrenom(String prenom) { this.prenom = prenom; }
    public LocalDate getDateNaissance() { return dateNaissance; }
    public void setDateNaissance(LocalDate dateNaissance) { this.dateNaissance = dateNaissance; }
    public String getFormation() { return formation; }
    public void setFormation(String formation) { this.formation = formation; }
    public String getPromo() { return promo; }
    public void setPromo(String promo) { this.promo = promo; }
    public Integer getAnneeDebut() { return anneeDebut; }
    public void setAnneeDebut(Integer anneeDebut) { this.anneeDebut = anneeDebut; }
    public Integer getAnneeSortie() { return anneeSortie; }
    public void setAnneeSortie(Integer anneeSortie) { this.anneeSortie = anneeSortie; }
    public List<String> getDiplomes() { return diplomes; }
    public void setDiplomes(List<String> diplomes) { this.diplomes = diplomes; }
    public String getAutresFormations() { return autresFormations; }
    public void setAutresFormations(String autresFormations) { this.autresFormations = autresFormations; }
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}