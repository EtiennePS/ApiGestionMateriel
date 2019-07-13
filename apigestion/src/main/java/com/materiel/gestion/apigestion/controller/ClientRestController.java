package com.materiel.gestion.apigestion.controller;

import com.materiel.gestion.apigestion.model.entite.Client;
import com.materiel.gestion.apigestion.service.IClientService;
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
@RequestMapping(path = "api/v1/clients", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class ClientRestController {
    @Autowired
    IClientService service;

    @GetMapping("/{id}")
    public Resource<Client> getById(@PathVariable Long id) {
        return toResource(service.getById(id));
    }

    @GetMapping("/")
    public Resources<Resource<Client>> getAll() {
        return toResources(service.getAll());
    }

    private Resource<Client> toResource(Client ai) {
        return new Resource<Client>(ai,
                linkTo(methodOn(ClientRestController.class).getById(ai.getId())).withSelfRel(),
                linkTo(methodOn(ClientRestController.class).getAll()).withRel("clients"));
    }

    private Resources<Resource<Client>> toResources(List<Client> p, Link... links) {
        List<Resource<Client>> clients = p.stream().map(this::toResource).collect(Collectors.toList());
        return new Resources<Resource<Client>>(clients, links);
    }
}
