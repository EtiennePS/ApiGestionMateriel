package com.materiel.gestion.apigestion.controller;


import com.materiel.gestion.apigestion.model.entite.Ville;
import com.materiel.gestion.apigestion.service.IVilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "api/v1/villes", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class VilleRestController {
    @Autowired
    IVilleService service;

    @GetMapping("/{id}")
    public Resource<Ville> getById(@PathVariable Long id) {
        return toResource(service.getById(id));
    }

    @GetMapping("/")
    public Resources<Resource<Ville>> getAll() {
        return toResources(service.getAll());
    }

    private Resource<Ville> toResource(Ville ta) {
        return new Resource<Ville>(ta,
                linkTo(methodOn(VilleRestController.class).getById(ta.getId())).withSelfRel(),
                linkTo(methodOn(VilleRestController.class).getAll()).withRel("ville"));
    }

    private Resources<Resource<Ville>> toResources(List<Ville> p, Link... links) {
        List<Resource<Ville>> villes = p.stream().map(this::toResource).collect(Collectors.toList());
        return new Resources<Resource<Ville>>(villes, links);
    }
}
