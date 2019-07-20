package com.materiel.gestion.apigestion.controller;

import com.materiel.gestion.apigestion.mapper.ResourceMapper;
import com.materiel.gestion.apigestion.model.entite.Client;
import com.materiel.gestion.apigestion.model.entite.Contact;
import com.materiel.gestion.apigestion.service.IClientService;
import com.materiel.gestion.apigestion.service.IContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "api/v1/clients", produces = MediaTypes.HAL_JSON_UTF8_VALUE)
public class ClientRestController {
    @Autowired
    IClientService service;
    
    @Autowired
    IContactService contactService;
    
    @Autowired
    ResourceMapper resourceMapper;

    @GetMapping("/{id}")
    public Resource<Client> getById(@PathVariable Long id) {
        return toResource(service.getById(id));
    }

    @GetMapping("/")
    public Resources<Resource<Client>> getAll() {
        return toResources(service.getAll());
    }
    
    @PostMapping("/{id}/contacts")
	public ResponseEntity<Resource<Contact>> create(@RequestBody Contact contact, @PathVariable Long id) throws URISyntaxException {
    	// On ajoute l'id client au contact
    	contact.setClient(new Client(id));
    	
		Resource<Contact> c = resourceMapper.toResource(contactService.create(contact));
		return ResponseEntity.created(new URI(c.getId().expand().getHref())).body(c);
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
