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

import com.materiel.gestion.apigestion.model.entite.Contact;
import com.materiel.gestion.apigestion.service.IContactService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "api/v1/contacts", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
@Api(tags="Contact Rest API")
public class ContactRestController {
	
	@Autowired
	IContactService service;
	
	@GetMapping("/{id}")
	public Resource<Contact> getById(@PathVariable Long id) {
		return toResource(service.getById(id));
	}
	
	@GetMapping("/")
	public Resources<Resource<Contact>> getAll() {
		return toResources(service.getAll());
	}
	
	private Resource<Contact> toResource(Contact ta) {
		return new Resource<Contact>(ta, 
				linkTo(methodOn(ContactRestController.class).getById(ta.getId())).withSelfRel(),
				linkTo(methodOn(ContactRestController.class).getAll()).withRel("contacts"));
	}
	
	private Resources<Resource<Contact>> toResources(List<Contact> p, Link ... links) {
		List<Resource<Contact>> contacts = p.stream().map(this::toResource).collect(Collectors.toList());
		return new Resources<Resource<Contact>>(contacts, links);
	}
}
