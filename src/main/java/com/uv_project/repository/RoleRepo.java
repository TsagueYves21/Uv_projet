package com.uv_project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.uv_project.entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {

	Role findByName(String roleName);

}
