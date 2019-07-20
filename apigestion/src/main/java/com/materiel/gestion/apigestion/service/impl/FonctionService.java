package com.materiel.gestion.apigestion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.model.entite.Fonction;
import com.materiel.gestion.apigestion.repository.FonctionRepository;
import com.materiel.gestion.apigestion.service.IFonctionService;



@Service
public class FonctionService extends GettableService<Fonction> implements IFonctionService {
	
	@Autowired
	private FonctionRepository repository;
	
}
