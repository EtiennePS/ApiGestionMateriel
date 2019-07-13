package com.materiel.gestion.apigestion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.model.entite.AdresseIp;
import com.materiel.gestion.apigestion.model.entite.Personne;
import com.materiel.gestion.apigestion.repository.AdresseIpRepository;
import com.materiel.gestion.apigestion.repository.PersonneRepository;
import com.materiel.gestion.apigestion.service.IAdresseIpService;
import com.materiel.gestion.apigestion.service.IPersonneService;

@Service
public class PersonneService extends GenericService<Personne> implements IPersonneService {
	
	@Autowired
	private PersonneRepository repository;
	
}
