package com.example.mscatalogue.dao;

import com.example.mscatalogue.entities.Produit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProduitRepository extends JpaRepository<Produit, Long> {
}