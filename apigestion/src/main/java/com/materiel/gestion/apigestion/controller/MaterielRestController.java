package com.materiel.gestion.apigestion.controller;

import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.service.IMaterielService;
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
@RequestMapping(path = "api/v1/materiels", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class MaterielRestController {
    @Autowired
    IMaterielService service;

    @GetMapping("/{id}")
    public Resource<Materiel> getById(@PathVariable Long id) {
        return toResource(service.getById(id));
    }

    @GetMapping("/")
    public Resources<Resource<Materiel>> getAll() {
        return toResources(service.getAll());
    }

    private Resource<Materiel> toResource(Materiel ai) {
        return new Resource<Materiel>(ai,
                linkTo(methodOn(MaterielRestController.class).getById(ai.getId())).withSelfRel(),
                linkTo(methodOn(MaterielRestController.class).getAll()).withRel("materiels"));
    }

    private Resources<Resource<Materiel>> toResources(List<Materiel> p, Link ... links) {
        List<Resource<Materiel>> materiels = p.stream().map(this::toResource).collect(Collectors.toList());
        return new Resources<Resource<Materiel>>(materiels, links);
    }
}
