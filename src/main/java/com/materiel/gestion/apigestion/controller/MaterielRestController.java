package com.materiel.gestion.apigestion.controller;

import com.materiel.gestion.apigestion.model.entite.Client;
import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.service.IMaterielService;
import com.materiel.gestion.apigestion.service.impl.MaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(path = "api/v1/materiels", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MaterielRestController {
    @Autowired
    IMaterielService service;
    @Autowired
    MaterielService materielService;

    @GetMapping("/{id}")
    public Materiel getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("")
    public List<Materiel> getAll() {
        return service.getAll();
    }

    @PostMapping("/{id}/materiels")
    public ResponseEntity<Materiel> create(@RequestBody Materiel materiel, @PathVariable Long id) throws URISyntaxException {
        // On ajoute l'id client au contact
        materiel.setClient(new Client(id));

        Materiel m = materielService.create(materiel);
        return ResponseEntity.created(new URI("api/v1/materiels/" + m.getId())).body(m);
    }
}
