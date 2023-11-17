package com.example.msorder.web;

import com.example.msorder.dao.CommandeRepository;
import com.example.msorder.dao.LigneCommandeRepository;
import com.example.msorder.entities.Commande;
import com.example.msorder.entities.LigneCommande;
import com.example.msorder.service.CatalogueClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order/v1")
public class OrderRestRessources {

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    private CatalogueClient catalogueClient;

    @GetMapping("/commandes")
    public List<Commande> listeCommandes() {
        return commandeRepository.findAll();
    }

    @GetMapping("/ligneCommandes")
    public List<LigneCommande> listeLigneCommandes() {
        return ligneCommandeRepository.findAll();
    }

    @GetMapping("/commandes/{id}")
    public Commande getCommande(@PathVariable(name="id") Long id) {
        return commandeRepository.findById(id).get();
    }
    @GetMapping("/ligneCommandes/{id}")
    public LigneCommande getLigneCommande(@PathVariable(name="id") Long id) {
        LigneCommande ligneCommande = ligneCommandeRepository.findById(id).get();
        ligneCommande.setProduit(catalogueClient.getProduit(ligneCommande.getProduitId()));
        return ligneCommande;
    }
}
