package com.materiel.gestion.apigestion.controller;

import com.materiel.gestion.apigestion.model.entite.Client;
import com.materiel.gestion.apigestion.model.entite.Contact;
import com.materiel.gestion.apigestion.service.IClientService;
import com.materiel.gestion.apigestion.service.IContactService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/clients", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class ClientRestController {
    @Autowired
    IClientService service;
    
    @Autowired
    IContactService contactService;

    @Autowired
    IClientService clientService;

    @GetMapping("/{id}")
    public Client getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Client> getAll() {
        return service.getAll();
    }
    
    @PostMapping
    public ResponseEntity<Client> create(@RequestBody Client client) throws URISyntaxException {
    	Client c = service.create(client);
    	return ResponseEntity.created(new URI("api/v1/clients/" + c.getId())).body(c);
    }
    
    @PostMapping("/{id}/contacts")
	public ResponseEntity<Contact> create(@RequestBody Contact contact, @PathVariable Long id) throws URISyntaxException {
    	// On ajoute l'id client au contact
    	contact.setClient(new Client(id));
    	
		Contact c = contactService.create(contact);
		return ResponseEntity.created(new URI("api/v1/contacts/" + c.getId())).body(c);
	}
    
    @PutMapping("/{id}/contacts/{idContact}")
    public Contact edit(@RequestBody Contact contact, @PathVariable Long id, @PathVariable Long idContact) {
    	contact.setClient(new Client(id));
    	contact.setId(idContact);
    	
    	Contact c = contactService.edit(contact);
    	return c;
    }

    @PutMapping("/{id}")
    public Client edit(@RequestBody Client client, @PathVariable Long id) {
        client.setId(id);
        Client c = clientService.edit(client);
        return c;
    }

}
