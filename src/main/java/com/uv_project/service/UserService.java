package com.uv_project.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.uv_project.entities.Produit;
import com.uv_project.entities.Role;
import com.uv_project.entities.User_Model;

public interface UserService {

	User_Model saveUser(User_Model user);
	List<User_Model> getAllUsers();
	Role saveRole(Role role);
	void addRoleToUser(String username,String roleName);
	void addProduitToUser(String username, Produit produit);
}
