package com.uv_project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.uv_project.entities.Role;
import com.uv_project.service.UserService;

@SpringBootApplication
public class UvProjectApplication implements CommandLineRunner {
	
	@Bean
	public static PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private UserService userService;
	public static void main(String[] args) {
		SpringApplication.run(UvProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		 userService.saveRole(new Role("ROLE_USER"));
		 userService.saveRole(new Role("ROLE_ADMIN"));
		 userService.saveRole(new Role("ROLE_SUPER_ADMIN"));		
	}

}
