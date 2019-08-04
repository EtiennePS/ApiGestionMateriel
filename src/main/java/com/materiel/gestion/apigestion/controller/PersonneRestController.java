package com.materiel.gestion.apigestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.materiel.gestion.apigestion.model.entite.Personne;
import com.materiel.gestion.apigestion.service.IPersonneService;

@RestController
@RequestMapping(path = "api/v1/personnes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//@Api(tags="Personne Rest API")
public class PersonneRestController {
	
	@Autowired
	IPersonneService service;
	
	@GetMapping("/{id}")
	public Personne getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/")
	public List<Personne> getAll() {
		return service.getAll();
	}
}
