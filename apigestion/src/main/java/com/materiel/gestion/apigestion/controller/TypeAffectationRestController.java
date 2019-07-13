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

import com.materiel.gestion.apigestion.model.entite.TypeAffectation;
import com.materiel.gestion.apigestion.service.ITypeAffectationService;

@RestController
@RequestMapping(path = "api/v1/typeaffectations", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
//@Api(tags="Type Affectation Rest API")
public class TypeAffectationRestController {
	
	@Autowired
	ITypeAffectationService service;
	
	@GetMapping("/{id}")
	public Resource<TypeAffectation> getById(@PathVariable Long id) {
		return toResource(service.getById(id));
	}
	
	@GetMapping("/")
	public Resources<Resource<TypeAffectation>> getAll() {
		return toResources(service.getAll());
	}
	
	private Resource<TypeAffectation> toResource(TypeAffectation ta) {
		return new Resource<TypeAffectation>(ta, 
				linkTo(methodOn(TypeAffectationRestController.class).getById(ta.getId())).withSelfRel(),
				linkTo(methodOn(TypeAffectationRestController.class).getAll()).withRel("typeaffectations"));
	}
	
	private Resources<Resource<TypeAffectation>> toResources(List<TypeAffectation> p, Link ... links) {
		List<Resource<TypeAffectation>> typeaffectations = p.stream().map(this::toResource).collect(Collectors.toList());
		return new Resources<Resource<TypeAffectation>>(typeaffectations, links);
	}
}
