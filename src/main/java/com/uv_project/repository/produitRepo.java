package com.uv_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uv_project.entities.Produit;

@Repository
public interface produitRepo extends JpaRepository<Produit, Long>  {

}
