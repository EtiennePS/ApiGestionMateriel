package com.materiel.gestion.apigestion.controller;

import com.materiel.gestion.apigestion.model.entite.TypeMateriel;
import com.materiel.gestion.apigestion.service.ITypeMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(path = "api/v1/typemateriels", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class TypeMaterielRestController {
    @Autowired
    ITypeMaterielService service;

    @GetMapping("/{id}")
    public TypeMateriel getById(@PathVariable Long id) {
        return service.getById(id);
    }

    @GetMapping("/")
    public List<TypeMateriel> getAll() {
        return service.getAll();
    }

}
