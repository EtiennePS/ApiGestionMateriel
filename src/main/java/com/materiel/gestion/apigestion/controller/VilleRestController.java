package com.materiel.gestion.apigestion.controller;


import com.materiel.gestion.apigestion.model.entite.Ville;
import com.materiel.gestion.apigestion.service.IVilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/villes", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class VilleRestController {
    @Autowired
    IVilleService service;

    @GetMapping("/{id}")
    public Ville getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/")
    public List<Ville> getAll() {
        return service.getAll();
    }

}
