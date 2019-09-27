package com.materiel.gestion.apigestion.controller;

import com.materiel.gestion.apigestion.model.entite.Interface;
import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.service.IMaterielService;
import com.materiel.gestion.apigestion.service.IIncidentService;
import com.materiel.gestion.apigestion.service.IInterfaceService;
import com.materiel.gestion.apigestion.model.entite.Incident;

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
    
    @Autowired
    IIncidentService incidentService;

    @GetMapping("/{id}")
    public Materiel getById(@PathVariable Long id) {
        return service.getById(id);
    }
    
    @GetMapping
    public List<Materiel> getAll() {
        return service.getAll();
    }
    
    /****** INTERFACES ******/
    
    @GetMapping("/{id}/interfaces")
    public List<Interface> getAllInterface(@PathVariable Long id) {
    	return interfaceService.getByMateriel(id);
    }
    
    @GetMapping("/{id}/interfaces/{idInterface}")
    public Interface getInterfaceById(@PathVariable Long id, @PathVariable Long idInterface) {
    	return interfaceService.getById(id, idInterface);
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
    	i.setId(idInterface);
        return interfaceService.edit(i);
    }
    
    @DeleteMapping("/{id}/interfaces/{idInterface}")
    public void deleteInterface(@PathVariable Long id, @PathVariable Long idInterface) {
	    Materiel m = new Materiel(id);
	    Interface i = new Interface(idInterface);
	    i.setMateriel(m);
	    
	    interfaceService.delete(i);
    }
    
    /****** INCIDENTS ******/
    
    @GetMapping("/{id}/incidents")
    public List<Incident> getAllIncidents(@PathVariable Long id) {
    	return incidentService.getByMateriel(id);
    }
    
    @GetMapping("/{id}/incidents/{idIncident}")
    public Incident getIncidentById(@PathVariable Long id, @PathVariable Long idIncident) {
    	return incidentService.getById(id, idIncident);
    }
    
    @PostMapping("/{id}/incidents")
    public ResponseEntity<Incident> createIncident(@PathVariable Long id, @RequestBody Incident inc) throws URISyntaxException {
    	inc.setMateriel(new Materiel(id));
    	Incident i = incidentService.create(inc);
    	return ResponseEntity.created(new URI("api/v1/materiels/" + i.getMateriel().getId() + "/incidents/"+ i.getId())).body(inc);
    }
    
    @PutMapping("/{id}/incidents/{idIncident}")
    public Incident editIncident(@PathVariable Long id, @PathVariable Long idIncident, @RequestBody Incident i) {
    	i.setId(idIncident);
    	i.setMateriel(new Materiel(id));
    	return incidentService.edit(i);
    }
    
    @PutMapping("/{id}/incidents/{idIncident}/clore")
    public Incident clore(@PathVariable Long id, @PathVariable Long idIncident) {
    	return incidentService.cloreIncident(id, idIncident);
    }
    
}   

