package com.materiel.gestion.apigestion.service;

import java.util.List;

import com.materiel.gestion.apigestion.model.entite.TypeAffectation;

public interface ITypeAffectationService {
	public TypeAffectation getById(Long id);
	
	public List<TypeAffectation> getAll();
}
