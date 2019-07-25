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

import com.materiel.gestion.apigestion.model.entite.Interface;
import com.materiel.gestion.apigestion.model.entite.TypeInterface;
import com.materiel.gestion.apigestion.service.IInterfaceService;
import com.materiel.gestion.apigestion.service.ITypeInterfaceService;

@RestController
@RequestMapping(path = "api/v1/typesinterfaces", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class TypeInterfaceRestController {

	@Autowired
	ITypeInterfaceService service;
	
	@GetMapping("/{id}")
	public Resource<TypeInterface> getById(@PathVariable Long id) {
		return toResource(service.getById(id));
	}
	
	@GetMapping("/")
	public Resources<Resource<TypeInterface>> getAll() {
		return toResources(service.getAll());
	}
	
	private Resource<TypeInterface> toResource(TypeInterface ti) {
		return new Resource<TypeInterface>(ti, 
				linkTo(methodOn(TypeInterfaceRestController.class).getById(ti.getId())).withSelfRel(),
				linkTo(methodOn(TypeInterfaceRestController.class).getAll()).withRel("typesinterfaces"));
	}
	
	private Resources<Resource<TypeInterface>> toResources(List<TypeInterface> p, Link ... links) {
		List<Resource<TypeInterface>> typesinterfaces = p.stream().map(this::toResource).collect(Collectors.toList());
		return new Resources<Resource<TypeInterface>>(typesinterfaces, links);
	}
}
