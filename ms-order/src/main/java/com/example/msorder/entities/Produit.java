package com.example.msorder.entities;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Produit {
    private Long id;
    private String nom;
    private String description;
    private double prix;
    private int quantite;
    private String image;
}
