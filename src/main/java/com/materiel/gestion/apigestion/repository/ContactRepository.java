package com.materiel.gestion.apigestion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.materiel.gestion.apigestion.model.entite.Client;
import com.materiel.gestion.apigestion.model.entite.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {
	
	public List<Contact> findByClient(Client c);

}
