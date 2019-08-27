package com.materiel.gestion.apigestion.controller;

import com.materiel.gestion.apigestion.model.entite.Client;
import com.materiel.gestion.apigestion.model.entite.Contact;
import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.model.entite.TypeMateriel;
import com.materiel.gestion.apigestion.service.IClientService;
import com.materiel.gestion.apigestion.service.IContactService;

import com.materiel.gestion.apigestion.service.IMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    IMaterielService materielService;

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
	public ResponseEntity<Contact> createContact(@RequestBody Contact contact, @PathVariable Long id) throws URISyntaxException {
    	// On ajoute l'id client au contact
    	contact.setClient(new Client(id));
    	
		Contact c = contactService.create(contact);
		return ResponseEntity.created(new URI("api/v1/contacts/" + c.getId())).body(c);
	}
    
    @PutMapping("/{id}/contacts/{idContact}")
    public Contact editContact(@RequestBody Contact contact, @PathVariable Long id, @PathVariable Long idContact) {
    	contact.setClient(new Client(id));
    	contact.setId(idContact);
    	
    	Contact c = contactService.edit(contact);
    	return c;
    }
    
    @DeleteMapping("/{id}/contacts/{idContact}")
    public void deleteContact(@PathVariable Long id, @PathVariable Long idContact) {
    	Client cl = new Client(id);
    	Contact c = new Contact(idContact);
    	c.setClient(cl);
    	
    	contactService.delete(c);
    }

    @PutMapping("/{id}")
    public Client edit(@RequestBody Client client, @PathVariable Long id) {
        client.setId(id);
        Client c = clientService.edit(client);
        return c;
    }
    
    @PostMapping("/{id}/materiels")
    public ResponseEntity<Materiel> create(@RequestBody Materiel materiel, @PathVariable Long id) throws URISyntaxException {
        // On ajoute l'id client au contact
        materiel.setClient(new Client(id));

        Materiel m = materielService.create(materiel);
        return ResponseEntity.created(new URI("api/v1/materiels/" + m.getId())).body(m);
    }
    @DeleteMapping("/{id}/materiels/{idMateriel}")
    public void deleteMateriel( @PathVariable Long id, @PathVariable Long idMateriel){
        Client ml = new Client(id);
        Materiel m = new Materiel(idMateriel);
        m.setClient(ml);
        materielService.delete(m);

    }
    @PutMapping("/{id}/materiels")
    public Materiel edit(@RequestBody Materiel materiel, @PathVariable Long id){
        materiel.setId(id);
        Materiel m = materielService.edit(materiel);
        return m;
    }

}
