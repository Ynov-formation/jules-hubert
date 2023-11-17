package com.example.msorder.service;

import com.example.msorder.entities.Produit;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "MS-CATALOGUE")
public interface CatalogueClient {

    @GetMapping( "/catalogue/v1/produits/{id}")
    @CircuitBreaker(name = "catalogueService", fallbackMethod = "getProduitFallback")
    Produit getProduit(@PathVariable("id") Long id);

    default Produit getProduitFallback(Long id, Exception e) {
        return Produit.builder().nom("Produit par défaut").build();
    }
}
