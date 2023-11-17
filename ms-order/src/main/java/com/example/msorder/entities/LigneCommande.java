package com.example.msorder.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "t_ligne_commande")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LigneCommande {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int quantite;
    private double prixUnitaire;
    private String designation;
    @ManyToOne
    @JsonIgnore
    private Commande commande;
    @Transient
    private Produit produit;
    private Long produitId;
}
