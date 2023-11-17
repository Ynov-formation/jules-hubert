package com.example.mscatalogue.web;

import com.example.mscatalogue.dao.CategorieRepository;
import com.example.mscatalogue.dao.ProduitRepository;
import com.example.mscatalogue.entities.Categorie;
import com.example.mscatalogue.entities.Produit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/catalogue/v1")
@Slf4j
public class CatalogueRestRessources {
    private ProduitRepository produitRepository;
    private CategorieRepository categorieRepository;

    public CatalogueRestRessources(ProduitRepository produitRepository, CategorieRepository categorieRepository) {
        this.produitRepository = produitRepository;
        this.categorieRepository = categorieRepository;
    }

    @GetMapping("/produits")
    public List<Produit> listeProduits() {
        log.info("Recuperation de la liste des produits");
        return produitRepository.findAll();
    }

    @GetMapping("/categories")
    public List<Categorie> listeCategories() {
        return categorieRepository.findAll();
    }

    @GetMapping("/produits/{id}")
    public Produit getProduit(@PathVariable(name="id") Long id) {
        log.info("Recuperation du produit avec l'id : {}", id);
        return produitRepository.findById(id).get();
    }

    @GetMapping("/categories/{id}")
    public Categorie getCategorie(@PathVariable(name="id") Long id) {
        return categorieRepository.findById(id).get();
    }
}
