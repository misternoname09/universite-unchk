package com.universite.unchk.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "partenaires")
public class Partenaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String secteur;
    private String typePartenariat;
    private String contact;

    public Partenaire() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNom() { return nom; }
    public void setNom(String nom) { this.nom = nom; }
    public String getSecteur() { return secteur; }
    public void setSecteur(String secteur) { this.secteur = secteur; }
    public String getTypePartenariat() { return typePartenariat; }
    public void setTypePartenariat(String typePartenariat) { this.typePartenariat = typePartenariat; }
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
}