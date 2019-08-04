package com.materiel.gestion.apigestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.materiel.gestion.apigestion.model.entite.Fonction;
import com.materiel.gestion.apigestion.service.IFonctionService;

@RestController
@RequestMapping(path = "api/v1/fonctions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//@Api(tags="Fonction Rest API")
public class FonctionRestController {
	
	@Autowired
	IFonctionService service;
	
	@GetMapping("/{id}")
	public Fonction getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/")
	public List<Fonction> getAll() {
		return service.getAll();
	}
	
}
