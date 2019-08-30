package com.materiel.gestion.apigestion.controller;

import com.materiel.gestion.apigestion.model.entite.Interface;
import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.service.IMaterielService;
import com.materiel.gestion.apigestion.service.IInterfaceService;
import com.materiel.gestion.apigestion.model.entite.Client;
import com.materiel.gestion.apigestion.model.entite.Contact;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/materiels", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MaterielRestController {
    @Autowired
    IMaterielService service;
    
    @Autowired
    IInterfaceService interfaceService;

    @GetMapping("/{id}")
    public Materiel getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping
    public List<Materiel> getAll() {
        return service.getAll();
    }
    
    @GetMapping("/{id}/interfaces")
    public List<Interface> getAllInterface(@PathVariable Long id) {
    	return interfaceService.getByMateriel(id);
    }
    
    @PostMapping("/{id}/interfaces")
    public ResponseEntity<Interface> create(@RequestBody Interface in, @PathVariable Long id) throws URISyntaxException {
    	in.setMateriel(new Materiel(id));
    	
    	Interface i = interfaceService.create(in);
		return ResponseEntity.created(new URI("api/v1/interfaces/" + i.getId())).body(i);
    }
    
    @PutMapping("/{id}/interfaces/{idInterface}")
    public Interface edit(@RequestBody Interface i, @PathVariable Long id, @PathVariable Long idInterface) {
    	i.setMateriel(new Materiel(id));
    	i.setId(id);
        return interfaceService.edit(i);
    }
    @DeleteMapping("/{id}/interfaces/{idInterface}")
    public void deleteInterface(@PathVariable Long id, @PathVariable Long idInterface) {
	    Materiel m = new Materiel(id);
	    Interface i = new Interface(idInterface);
	    i.setMateriel(m);
	    
	    interfaceService.delete(i);
    }
}   

