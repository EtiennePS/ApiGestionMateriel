package com.materiel.gestion.apigestion.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.materiel.gestion.apigestion.model.entite.User;

public interface UserRepository extends JpaRepository<User, Long> {
	public Optional<User> findUserByUsername(String username);
}
