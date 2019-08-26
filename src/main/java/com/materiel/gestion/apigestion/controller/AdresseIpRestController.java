package com.materiel.gestion.apigestion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.materiel.gestion.apigestion.model.entite.AdresseIp;
import com.materiel.gestion.apigestion.service.IAdresseIpService;

@RestController
@RequestMapping(path = "api/v1/adresseips", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class AdresseIpRestController {
	
	@Autowired
	IAdresseIpService service;
	
	@GetMapping("/{id}")
	public AdresseIp getById(@PathVariable Long id) {
		return service.getById(id);
	}
	
	@GetMapping("/")
	public List<AdresseIp> getAll() {
		return service.getAll();
	}
	
}
