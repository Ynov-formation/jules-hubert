package com.example.mscatalogue;

import com.example.mscatalogue.dao.CategorieRepository;
import com.example.mscatalogue.dao.ProduitRepository;
import com.example.mscatalogue.entities.Categorie;
import com.example.mscatalogue.entities.Produit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class MsCatalogueApplication implements CommandLineRunner {

    @Autowired
    private ProduitRepository produitRepository;
    @Autowired
    private CategorieRepository categorieRepository;
    public static void main(String[] args) {
        SpringApplication.run(MsCatalogueApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
//                Categorie categorie1 = Categorie.builder()
//                .nom("Ordinateurs")
//                .description("Description de la categorie ordinateurs")
//                .build();
//        Categorie categorie2 = Categorie.builder()
//                .nom("Smatphones")
//                .description("Description de la categorie smartphones")
//                .build();
//        categorie1 = categorieRepository.save(categorie1);
//        categorie2 = categorieRepository.save(categorie2);
//        produitRepository.saveAll(List.of(
//                Produit.builder()
//                        .nom("PC Asus")
//                        .description("Description du PC Asus")
//                        .prix(10.0)
//                        .categorie(categorie1)
//                        .build(),
//                Produit.builder()
//                        .nom("PC Dell")
//                        .description("Description du PC Dell")
//                        .prix(20.0)
//                        .categorie(categorie1)
//                        .build(),
//                Produit.builder()
//                        .nom("Iphone 12")
//                        .description("Description du iphone 12")
//                        .prix(20.0)
//                        .categorie(categorie2)
//                        .build()
//        ));
    }
}
