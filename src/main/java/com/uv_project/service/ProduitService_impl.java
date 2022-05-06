package com.uv_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uv_project.entities.Produit;
import com.uv_project.entities.User_Model;
import com.uv_project.repository.UserRepo;
@Service
public class ProduitService_impl implements ProduitService {
	
	@Autowired
	private UserRepo userRepo;
	@Autowired 
	private UserService userService;

	@Override
	public Produit saveProduit(String username, Produit produit) {
		User_Model user = userRepo.findByUsername(username);
		if(user.getUsername() == null) {
			throw new IllegalStateException(
					"User with user name "+ username +"Does not exists!!");
		}
		userService.addProduitToUser(user.getUsername(), produit);
		return produit;
	}

}
