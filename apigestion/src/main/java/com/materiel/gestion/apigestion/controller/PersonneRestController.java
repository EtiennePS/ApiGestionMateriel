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

import com.materiel.gestion.apigestion.model.entite.Personne;
import com.materiel.gestion.apigestion.service.IPersonneService;

@RestController
@RequestMapping(path = "api/v1/personnes", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
//@Api(tags="Personne Rest API")
public class PersonneRestController {
	
	@Autowired
	IPersonneService service;
	
	@GetMapping("/{id}")
	public Resource<Personne> getById(@PathVariable Long id) {
		return toResource(service.getById(id));
	}
	
	@GetMapping("/")
	public Resources<Resource<Personne>> getAll() {
		return toResources(service.getAll());
	}
	
	private Resource<Personne> toResource(Personne p) {
		return new Resource<Personne>(p, 
				linkTo(methodOn(PersonneRestController.class).getById(p.getId())).withSelfRel(),
				linkTo(methodOn(PersonneRestController.class).getAll()).withRel("personnes"));
	}
	
	private Resources<Resource<Personne>> toResources(List<Personne> p, Link ... links) {
		List<Resource<Personne>> personnes = p.stream().map(this::toResource).collect(Collectors.toList());
		return new Resources<Resource<Personne>>(personnes, links);
	}
}
