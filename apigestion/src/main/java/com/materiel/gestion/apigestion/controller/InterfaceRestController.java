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
import com.materiel.gestion.apigestion.service.IInterfaceService;

@RestController
@RequestMapping(path = "api/v1/interfaces", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class InterfaceRestController {

	@Autowired
	IInterfaceService service;
	
	@GetMapping("/{id}")
	public Resource<Interface> getById(@PathVariable Long id) {
		return toResource(service.getById(id));
	}
	
	@GetMapping("/")
	public Resources<Resource<Interface>> getAll() {
		return toResources(service.getAll());
	}
	
	private Resource<Interface> toResource(Interface i) {
		return new Resource<Interface>(i, 
				linkTo(methodOn(InterfaceRestController.class).getById(i.getId())).withSelfRel(),
				linkTo(methodOn(InterfaceRestController.class).getAll()).withRel("interfaces"));
	}
	
	private Resources<Resource<Interface>> toResources(List<Interface> p, Link ... links) {
		List<Resource<Interface>> interfaces = p.stream().map(this::toResource).collect(Collectors.toList());
		return new Resources<Resource<Interface>>(interfaces, links);
	}
}

