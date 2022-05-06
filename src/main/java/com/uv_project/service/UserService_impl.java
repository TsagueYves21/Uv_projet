package com.uv_project.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.uv_project.entities.Produit;
import com.uv_project.entities.Role;
import com.uv_project.entities.Status_Value;
import com.uv_project.entities.User_Model;
import com.uv_project.repository.RoleRepo;
import com.uv_project.repository.UserRepo;

import lombok.RequiredArgsConstructor;

@Service @Transactional
@RequiredArgsConstructor
public class UserService_impl implements UserService,UserDetailsService {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleRepo roleRepo;
	@Autowired
	private PasswordEncoder passWordEncoder;
	@Override
	public User_Model saveUser(User_Model user) {
		Optional<User_Model> userByEmail = userRepo.findByEmail(user.getEmail());
		  if(userByEmail.isPresent()) {
			  throw new IllegalStateException(" Email taken !!!");
		  }
			user.setPassword(passWordEncoder.encode(user.getPassword()));
            user.setStatus(Status_Value.ACTIVE);
		  userRepo.save(user);
		  addRoleToUser(user.getUsername(), "ROLE_USER");
		  return user;
	}
	
	@Override
	public List<User_Model> getAllUsers() {
		return userRepo.findAll();
	}
	@Override
	public Role saveRole(Role role) {
		return roleRepo.save(role);
	}
	@Override
	public void addRoleToUser(String username, String roleName) {
		User_Model   user = userRepo.findByUsername(username);
	     Role  role = roleRepo.findByName(roleName);
	      user.getRole().add(role);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User_Model user = userRepo.findByUsername(username);
		if(user==null || user.getStatus().equals(Status_Value.DESACTIVE)) {
			throw new UsernameNotFoundException("user not found in data base");
		}
		boolean enabled = user.isEnabled();
		Collection<SimpleGrantedAuthority> autorities = new ArrayList<>();
		user.getRole().forEach(role ->{autorities.add(new SimpleGrantedAuthority(role.getName()));});
//		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), autorities);
		UserDetails user1 = User.withUsername(user.getUsername())
				.password(user.getPassword()).authorities(autorities).disabled(enabled).build();
		return user1;
		
	}

	@Override
	public void addProduitToUser(String username, Produit produit) {
		User_Model user = userRepo.findByUsername(username);
		user.getProduit().add(produit);		
	}

}
