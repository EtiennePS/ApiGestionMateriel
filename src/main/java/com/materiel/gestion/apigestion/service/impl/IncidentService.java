package com.materiel.gestion.apigestion.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.model.entite.Incident;
import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.model.entite.Status;
import com.materiel.gestion.apigestion.repository.IncidentRepository;
import com.materiel.gestion.apigestion.service.IIncidentService;
import com.materiel.gestion.apigestion.service.IMaterielService;
import com.materiel.gestion.apigestion.service.IStatusService;
import com.materiel.gestion.apigestion.exception.CreationException;
import com.materiel.gestion.apigestion.exception.DataOwnerException;
import com.materiel.gestion.apigestion.exception.EditionException;
import com.materiel.gestion.apigestion.model.entite.Client;

@Service
public class IncidentService extends GettableService<Incident> implements IIncidentService{
	
	@Autowired
	private IncidentRepository repository;
	
	@Autowired
	private IMaterielService materielService;
	
	@Autowired
	private IStatusService statusService;
	
	@Override
	public Incident getById(Long idMateriel, Long id) {
		checkMateriel(new Incident(id, new Materiel(idMateriel)));
		return getById(id);
	}
	
	@Override
	public Incident create(Incident i) {
		
		if(i.getId() != null) {
			throw new CreationException("Il est interdit de renseigner l'id lors de la création d'Incident");
		}
		else if(i.getMateriel() == null || i.getMateriel().getId() == null) {
			throw new CreationException("Le matériel lié à l'incident n'est pas renseigné");
		}
		
		i.setMateriel(materielService.getById(i.getMateriel().getId()));
		i.setStatus(statusService.getById(Status.OUVERT.getId()));
		i.setDateCreation(new Date());
		i.setDateCloture(null);
		return repository.save(i);
	}

	@Override
	public Incident edit(Incident i) {
		if(i == null || i.getId() == null) {
			throw new EditionException("Impossible de mettre à jour un incident vide");
		}
		
		checkMateriel(i);
		
		Incident incident = repository.getOne(i.getId());
		
		if(incident.getStatus().getId() == Status.FERME.getId()) {
			throw new EditionException("Impossible de mettre à jour un incident clôt");
		}
		
		// Seul 3 champs sont librement modifiables
		incident.setLibelle(i.getLibelle());
		incident.setSymptome(i.getSymptome());
		incident.setResolution(i.getResolution());
		
		return repository.save(incident);
	}
	
	@Override
	public Incident cloreIncident(Long idMateriel, Long idIncident) {
		checkMateriel(new Incident(idIncident, new Materiel(idMateriel)));
		
		Incident i = repository.getOne(idIncident);
		
		if(i.getResolution() == null) {
			throw new EditionException("Impossible de clôturer un incident non résolu.");
		}
		
		i.setStatus(statusService.getById(Status.FERME.getId()));
		i.setDateCloture(new Date());
		return repository.save(i);
	}

	@Override
	public List<Incident> getByMateriel(Long idMateriel) {
		return repository.findByMateriel(new Materiel(idMateriel));
	}

	@Override
	public List<Incident> getByStatus(Status status) {
		return repository.findByStatus(status);
	}

	@Override
	public List<Incident> getByClientAndDates(Client client, Date dateDebut, Date dateFin) {
		return this.getByClientAndDates(client.getId(), dateDebut, dateFin);
	}

	@Override
	public List<Incident> getByClientAndDates(Long idClient, Date dateDebut, Date dateFin) {
		return repository.getAllByClientBetween(dateDebut, dateFin, idClient);
	}
	
	private void checkMateriel(Incident i){
        Incident incident = repository.getOne(i.getId());
        if (incident.getMateriel().getId()!= i.getMateriel().getId()){
            throw new DataOwnerException("L'incident n'appartient pas à ce matériel");
        }
    }

}
