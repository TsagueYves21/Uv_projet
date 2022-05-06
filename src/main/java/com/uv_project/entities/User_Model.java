package com.uv_project.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
public class User_Model {
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@Column(nullable = false)
	private String username;
	@Column(nullable = false)
	private String email;
	private Sexe sexe;
	private Status_Value status;
	private String password;
	private String photo;
	private boolean enabled;
	@ManyToMany(fetch = FetchType.EAGER)
	private Collection<Role> role = new ArrayList<>();
	
	 @OneToMany( cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)
	 @JoinColumn(name = "users_id")
	 private List<Produit> produit = new ArrayList<>() ;

	public User_Model(String name, String username, String email, Sexe sexe, String password, String photo,
			boolean enabled, Collection<Role> role, List<Produit> produit) {
		super();
		this.name = name;
		this.username = username;
		this.email = email;
		this.sexe = sexe;
		this.password = password;
		this.photo = photo;
		this.enabled = enabled;
		this.role = role;
		this.produit = produit;
	}
	
	 
	 
	}
