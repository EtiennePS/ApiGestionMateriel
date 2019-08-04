package com.materiel.gestion.apigestion.controller;

import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.service.IMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/materiels", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class MaterielRestController {
    @Autowired
    IMaterielService service;

    @GetMapping("/{id}")
    public Materiel getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/")
    public List<Materiel> getAll() {
        return service.getAll();
    }
}
