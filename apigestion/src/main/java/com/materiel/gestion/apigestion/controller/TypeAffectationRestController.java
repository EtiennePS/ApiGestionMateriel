package com.materiel.gestion.apigestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.materiel.gestion.apigestion.model.entite.TypeAffectation;
import com.materiel.gestion.apigestion.service.ITypeAffectationService;

@RestController
@RequestMapping(path = "api/v1/typeaffectations", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class TypeAffectationRestController {
	
	@Autowired
	ITypeAffectationService service;
	
	@GetMapping("/{id}")
	public TypeAffectation getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/")
	public List<TypeAffectation> getAll() {
		return service.getAll();
	}
	
}
