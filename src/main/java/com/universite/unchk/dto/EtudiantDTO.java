package com.universite.unchk.dto;

import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Data
public class EtudiantDTO {
    private Long id;
    private String ine;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String formation;
    private String promo;
    private Integer anneeDebut;
    private Integer anneeSortie;
    private List<String> diplomes;
    private String autresFormations;
}