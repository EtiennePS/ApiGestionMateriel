package com.materiel.gestion.apigestion.mapper;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.stereotype.Component;

import com.materiel.gestion.apigestion.controller.ClientRestController;
import com.materiel.gestion.apigestion.controller.ContactRestController;
import com.materiel.gestion.apigestion.model.entite.Contact;

@Component
public class ResourceMapper {
	
	public Resource<Contact> toResource(Contact c) {
		return new Resource<Contact>(c, 
				linkTo(methodOn(ContactRestController.class).getById(c.getId())).withSelfRel(),
				linkTo(methodOn(ClientRestController.class).getById(c.getId())).withRel("client"));	
	}
	
	public Resources<Resource<Contact>> toResources(List<Contact> c, Link ... links) {
		Link[] l = { linkTo(methodOn(ContactRestController.class).getAll()).withSelfRel() };
		links = links.length > 0 ? links : l;
		
		List<Resource<Contact>> contacts = c.stream().map(this::toResource).collect(Collectors.toList());
		return new Resources<Resource<Contact>>(contacts, links);
	}
	
}
