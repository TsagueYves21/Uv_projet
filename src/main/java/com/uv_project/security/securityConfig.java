package com.uv_project.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.uv_project.filter.CustomAuthenticationFilter;
import com.uv_project.filter.CustomAuthorizationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class securityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private BCryptPasswordEncoder bcryptPasswordEncoder;
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bcryptPasswordEncoder);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.authorizeRequests().antMatchers("/login/**","/api/users/save/**").permitAll();
		/*
		 * user Authorization request per Role
		 */ 
		
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/users/toto/**").hasAnyAuthority("ROLE_SUPER_ADMIN","ROLE_ADMIN","ROLE_USER");
		
		/*
		 * produit Authorization request per Role
		 */
		http.authorizeRequests().antMatchers(HttpMethod.GET,"/api/produit/**").hasAnyAuthority("ROLE_SUPER_ADMIN","ROLE_ADMIN","ROLE_USER");
		http.authorizeRequests().antMatchers(HttpMethod.POST,"/api/produit/**").hasAnyAuthority("ROLE_SUPER_ADMIN","ROLE_ADMIN","ROLE_USER");
		
		http.authorizeRequests().anyRequest().authenticated();
		http.addFilter(new CustomAuthenticationFilter(AuthenticationanagerBean()));
	    http.addFilterBefore(new CustomAuthorizationFilter(),UsernamePasswordAuthenticationFilter.class);
	
	}

	@Bean
	public AuthenticationManager AuthenticationanagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
}
