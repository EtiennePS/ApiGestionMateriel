package com.materiel.gestion.apigestion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.model.entite.Personne;
import com.materiel.gestion.apigestion.repository.PersonneRepository;
import com.materiel.gestion.apigestion.service.IPersonneService;

@Service
public class PersonneService extends GenericService<Personne> implements IPersonneService {
	
	@Autowired
	private PersonneRepository repository;
	
}
