package com.materiel.gestion.apigestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.materiel.gestion.apigestion.model.entite.Fonction;
import com.materiel.gestion.apigestion.service.IFonctionService;

@RestController
@RequestMapping(path = "api/v1/fonctions", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
//@Api(tags="Fonction Rest API")
public class FonctionRestController {
	
	@Autowired
	IFonctionService service;
	
	@GetMapping("/{id}")
	public Fonction getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@GetMapping
	public List<Fonction> getAll() {
		List<Fonction> result = service.getAll();
		System.out.println(result.get(0).getLibelle());
		return result;
	}
	
}
