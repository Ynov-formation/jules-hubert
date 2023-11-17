package com.example.mscatalogue.dao;

import com.example.mscatalogue.entities.Categorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategorieRepository extends JpaRepository<Categorie, Long> {
}