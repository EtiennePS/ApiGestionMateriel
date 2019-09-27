package com.materiel.gestion.apigestion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.model.entite.TypeAffectation;
import com.materiel.gestion.apigestion.repository.TypeAffectationRepository;
import com.materiel.gestion.apigestion.service.ITypeAffectationService;

@Service
public class TypeAffectationService extends GettableService<TypeAffectation> implements ITypeAffectationService {
	
	@SuppressWarnings("unused")
	@Autowired
	private TypeAffectationRepository repository;
}
