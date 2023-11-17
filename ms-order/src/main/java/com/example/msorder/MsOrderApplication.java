package com.example.msorder;

import com.example.msorder.dao.CommandeRepository;
import com.example.msorder.dao.LigneCommandeRepository;
import com.example.msorder.entities.Commande;
import com.example.msorder.entities.LigneCommande;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class MsOrderApplication implements CommandLineRunner {

    @Autowired
    private CommandeRepository commandeRepository;
    @Autowired
    private LigneCommandeRepository ligneCommandeRepository;
    public static void main(String[] args) {
        SpringApplication.run(MsOrderApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Commande commande1 = Commande.builder()
                .date(LocalDateTime.now())
                .designation("Commande 1")
                .description("Description de la commande 1")
                .build();
        Commande commande2 = Commande.builder()
                .date(LocalDateTime.now())
                .designation("Commande 2")
                .description("Description de la commande 2")
                .build();
        commande1 = commandeRepository.save(commande1);
        commande2 = commandeRepository.save(commande2);
        List.of(
                        LigneCommande.builder()
                                .quantite(10)
                                .designation("Ligne commande 1 de la commande 1")
                                .prixUnitaire(10.0)
                                .commande(commande1)
                                .build(),
                        LigneCommande.builder()
                                .quantite(7)
                                .designation("Ligne commande 2 de la commande 1")
                                .prixUnitaire(14.7)
                                .commande(commande1)
                                .build(),
                        LigneCommande.builder()
                                .quantite(3)
                                .designation("Ligne commande 1 de la commande 2")
                                .prixUnitaire(7.2)
                                .commande(commande2)
                                .build(),
                        LigneCommande.builder()
                                .quantite(17)
                                .designation("Ligne commande 2 de la commande 2")
                                .prixUnitaire(9.5)
                                .commande(commande2)
                                .build(),
                        LigneCommande.builder()
                                .quantite(14)
                                .designation("Ligne commande 3 de la commande 2")
                                .prixUnitaire(12.3)
                                .commande(commande2)
                                .build())
                .forEach(ligneCommandeRepository::save);
    }
}
