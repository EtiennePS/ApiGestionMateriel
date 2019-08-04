package com.materiel.gestion.apigestion.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import com.materiel.gestion.apigestion.model.entite.User;

public interface IUserService extends UserDetailsService {
	public UserDetails loadUserByUsername(String username);
	public User save(User user);
}
