package com.uv_project.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Produit {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
		private Long id;
		private String name;
	    private String description;
	    private float prix;
	    private String photo;
    
    public Produit(String name, String description, float prix, String photo) {
		super();
		this.name = name;
		this.description = description;
		this.prix = prix;
		this.photo = photo;
	}
}
