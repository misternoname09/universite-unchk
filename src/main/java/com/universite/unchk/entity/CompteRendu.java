package com.universite.unchk.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "comptes_rendus")
public class CompteRendu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titre;
    private String contenu;
    private LocalDateTime dateCreation;

    // Types possibles : REUNION, RENCONTRE, SEMINAIRE, WEBINAIRE, CONSEIL
    private String type;

    private String auteur;
    private String documentJoint; // chemin ou nom du fichier

    // Rôles autorisés pour accéder au compte rendu
    @ElementCollection
    @CollectionTable(name = "compte_rendu_roles", joinColumns = @JoinColumn(name = "compte_rendu_id"))
    @Column(name = "role")
    private Set<String> rolesAutorises = new HashSet<>();

    // Constructeur par défaut
    public CompteRendu() {}

    // Getters et setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitre() { return titre; }
    public void setTitre(String titre) { this.titre = titre; }

    public String getContenu() { return contenu; }
    public void setContenu(String contenu) { this.contenu = contenu; }

    public LocalDateTime getDateCreation() { return dateCreation; }
    public void setDateCreation(LocalDateTime dateCreation) { this.dateCreation = dateCreation; }

    public String getType() { return type; }
    public void setType(String type) { this.type = type; }

    public String getAuteur() { return auteur; }
    public void setAuteur(String auteur) { this.auteur = auteur; }

    public String getDocumentJoint() { return documentJoint; }
    public void setDocumentJoint(String documentJoint) { this.documentJoint = documentJoint; }

    public Set<String> getRolesAutorises() { return rolesAutorises; }
    public void setRolesAutorises(Set<String> rolesAutorises) { this.rolesAutorises = rolesAutorises; }
}
