package com.materiel.gestion.apigestion.controller;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.materiel.gestion.apigestion.model.entite.Fonction;
import com.materiel.gestion.apigestion.service.IFonctionService;

@RestController
@RequestMapping(path = "api/v1/fonctions", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
//@Api(tags="Fonction Rest API")
public class FonctionRestController {
	
	@Autowired
	IFonctionService service;
	
	@GetMapping("/{id}")
	public Resource<Fonction> getById(@PathVariable Long id) {
		return toResource(service.getById(id));
	}
	
	@GetMapping("/")
	public Resources<Resource<Fonction>> getAll() {
		return toResources(service.getAll());
	}
	
	private Resource<Fonction> toResource(Fonction f) {
		return new Resource<Fonction>(f, 
				linkTo(methodOn(FonctionRestController.class).getById(f.getId())).withSelfRel(),
				linkTo(methodOn(FonctionRestController.class).getAll()).withRel("fonctions"));
	}
	
	private Resources<Resource<Fonction>> toResources(List<Fonction> lf, Link ... links) {
		List<Resource<Fonction>> fonctions = lf.stream().map(this::toResource).collect(Collectors.toList());
		return new Resources<Resource<Fonction>>(fonctions, links);
	}
}
