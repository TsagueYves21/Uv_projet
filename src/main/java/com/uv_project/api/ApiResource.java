package com.uv_project.api;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uv_project.entities.Produit;
import com.uv_project.entities.Sexe;
import com.uv_project.entities.User_Model;
import com.uv_project.fonctions.Helpers;
import com.uv_project.service.ProduitService;
import com.uv_project.service.UserService;

@RestController
@RequestMapping("api/")
public class ApiResource {

	@Autowired
	private UserService userService;
	
	@Autowired
	private Helpers helpers;
	@Autowired 
	private ProduitService produitService;
	
	@PostMapping("/users/save")
	public User_Model  saveUser(@RequestParam("user") String user,
            @RequestParam(name = "file", required = false) MultipartFile file)  throws JsonParseException,JsonMappingException,Exception {

		
		User_Model users = new ObjectMapper().readValue(user,User_Model.class);
		try {
			helpers.addUserImage(file);
			String fileName = file.getOriginalFilename();
			String newFileName = FilenameUtils.getBaseName(fileName)+"."+FilenameUtils.getExtension(fileName);
			users.setPhoto(newFileName);
			
		}catch (NullPointerException e) {
			if(users.getSexe()==Sexe.HOMME) {
			users.setPhoto("h.png");
			return userService.saveUser(users);
					}
		if(users.getSexe()==Sexe.FEMME) {
			users.setPhoto("f.webp");
			return userService.saveUser(users);
		 }
		}
		  return userService.saveUser(users);
		
		
	}
	@GetMapping("/users/get")
	public List<User_Model> getAllUsers(){
		return userService.getAllUsers();
	}
	
	@PostMapping("/produit/save")
	public Produit saveContact(@RequestParam("produit") String produit,
			                                     @RequestParam(name="file",required=false) MultipartFile file,
			                                     @RequestParam(name="username",required = true) String username) throws JsonParseException,JsonMappingException,Exception {

		Produit produits = new ObjectMapper().readValue(produit,Produit.class);
		try {
		helpers.addProduitImage(file);
			String fileName = file.getOriginalFilename();
			String newFileName = FilenameUtils.getBaseName(fileName)+"."+FilenameUtils.getExtension(fileName);
			produits.setPhoto(newFileName);
			
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
		return produitService.saveProduit(username, produits);
		
	}
	
   
}
