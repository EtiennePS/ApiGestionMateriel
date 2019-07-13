package com.materiel.gestion.apigestion.controller;

import com.materiel.gestion.apigestion.model.entite.TypeMateriel;
import com.materiel.gestion.apigestion.service.ITypeMaterielService;
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
@RequestMapping(path = "api/v1/typemateriels", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class TypeMaterielRestController {
    @Autowired
    ITypeMaterielService service;

    @GetMapping("/{id}")
    public Resource<TypeMateriel> getById(@PathVariable Long id) {
        return toResource(service.getById(id));
    }

    @GetMapping("/")
    public Resources<Resource<TypeMateriel>> getAll() {
        return toResources(service.getAll());
    }

    private Resource<TypeMateriel> toResource(TypeMateriel ai) {
        return new Resource<TypeMateriel>(ai,
                linkTo(methodOn(TypeMaterielRestController.class).getById(ai.getId())).withSelfRel(),
                linkTo(methodOn(TypeMaterielRestController.class).getAll()).withRel("typemateriels"));
    }

    private Resources<Resource<TypeMateriel>> toResources(List<TypeMateriel> p, Link ... links) {
        List<Resource<TypeMateriel>> typemateriels = p.stream().map(this::toResource).collect(Collectors.toList());
        return new Resources<Resource<TypeMateriel>>(typemateriels, links);
    }
}
