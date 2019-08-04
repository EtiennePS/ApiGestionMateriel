package com.materiel.gestion.apigestion.service.impl;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.model.entite.User;
import com.materiel.gestion.apigestion.repository.UserRepository;
import com.materiel.gestion.apigestion.service.IUserService;

@Service
public class UserService implements IUserService {
	
	@Autowired
	private UserRepository repository;

	@Autowired
	private PasswordEncoder bcryptEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> ou = repository.findUserByUsername(username);
		
		if(!ou.isPresent()) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		
		return new org.springframework.security.core.userdetails.User(
				ou.get().getUsername(), ou.get().getPassword(), new ArrayList<>());
	}
	
	public User save(User user) {
		user.setPassword(bcryptEncoder.encode(user.getPassword()));
		return repository.save(user);
	}
}
