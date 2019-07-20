package com.materiel.gestion.apigestion.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.materiel.gestion.apigestion.mapper.ResourceMapper;
import com.materiel.gestion.apigestion.model.entite.Contact;
import com.materiel.gestion.apigestion.service.IContactService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "api/v1/contacts", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
@Api(tags="Contact Rest API")
public class ContactRestController {
	
	@Autowired
	IContactService service;
	
	@Autowired
	ResourceMapper resourceMapper;
	
	@GetMapping("/{id}")
	public Resource<Contact> getById(@PathVariable Long id) {
		return resourceMapper.toResource(service.getById(id));
	}
	
	@GetMapping
	public Resources<Resource<Contact>> getAll() {
		return resourceMapper.toResources(service.getAll());
	}
		
}
