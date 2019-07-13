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

import com.materiel.gestion.apigestion.model.entite.AdresseIp;
import com.materiel.gestion.apigestion.service.IAdresseIpService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "api/v1/adresseips", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class AdresseIpRestController {
	
	@Autowired
	IAdresseIpService service;
	
	@GetMapping("/{id}")
	public Resource<AdresseIp> getById(@PathVariable Long id) {
		return toResource(service.getById(id));
	}
	
	@GetMapping("/")
	public Resources<Resource<AdresseIp>> getAll() {
		return toResources(service.getAll());
	}
	
	private Resource<AdresseIp> toResource(AdresseIp ai) {
		return new Resource<AdresseIp>(ai, 
				linkTo(methodOn(AdresseIpRestController.class).getById(ai.getId())).withSelfRel(),
				linkTo(methodOn(AdresseIpRestController.class).getAll()).withRel("adresseips"));
	}
	
	private Resources<Resource<AdresseIp>> toResources(List<AdresseIp> p, Link ... links) {
		List<Resource<AdresseIp>> adresseips = p.stream().map(this::toResource).collect(Collectors.toList());
		return new Resources<Resource<AdresseIp>>(adresseips, links);
	}
	
}
