package com.materiel.gestion.apigestion.controller;

import com.materiel.gestion.apigestion.model.entite.Interface;
import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.service.IMaterielService;
import com.materiel.gestion.apigestion.service.IInterfaceService;
import com.materiel.gestion.apigestion.model.entite.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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

@RestController
@RequestMapping(path = "api/v1/materiels", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MaterielRestController {
    @Autowired
    IMaterielService service;
    
    @Autowired
    IInterfaceService interfaceService;

    @GetMapping("/{id}")
    public Materiel getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("")
    public List<Materiel> getAll() {
        return service.getAll();
    }
    
    @PostMapping("/{id}/interfaces")
    public ResponseEntity<Interface> create(@RequestBody Interface in, @PathVariable Long id) throws URISyntaxException {
    	in.setMateriel(new Materiel(id));
    	
    	Interface i = interfaceService.create(in);
		return ResponseEntity.created(new URI("api/v1/interfaces/" + i.getId())).body(i);
    }



}
    

