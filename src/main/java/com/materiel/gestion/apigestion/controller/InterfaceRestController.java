package com.materiel.gestion.apigestion.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.materiel.gestion.apigestion.model.entite.Interface;
import com.materiel.gestion.apigestion.service.IInterfaceService;

@RestController
@RequestMapping(path = "api/v1/interfaces", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class InterfaceRestController {

	@Autowired
	IInterfaceService service;
	
	@GetMapping("/{id}")
	public Interface getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/")
	public List<Interface> getAll() {
		return service.getAll();
	}
}

