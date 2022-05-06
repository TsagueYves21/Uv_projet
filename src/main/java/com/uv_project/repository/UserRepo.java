package com.uv_project.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uv_project.entities.User_Model;

@Repository
public interface UserRepo extends JpaRepository<User_Model, Long> {

	User_Model findByUsername(String username);
	Optional<User_Model> findByEmail(String email);
}
