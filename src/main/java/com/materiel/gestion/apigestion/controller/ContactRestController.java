package com.materiel.gestion.apigestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.materiel.gestion.apigestion.model.entite.Contact;
import com.materiel.gestion.apigestion.service.IContactService;

import io.swagger.annotations.Api;

@RestController
@RequestMapping(path = "api/v1/contacts", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(tags="Contact Rest API")
public class ContactRestController {
	
	@Autowired
	IContactService service;
	
	@GetMapping("/{id}")
	public Contact getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@GetMapping
	public List<Contact> getAll() {
		return service.getAll();
	}
		
}
