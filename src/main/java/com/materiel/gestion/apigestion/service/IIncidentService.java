package com.materiel.gestion.apigestion.service;

import java.util.Date;
import java.util.List;

import com.materiel.gestion.apigestion.model.entite.Client;
import com.materiel.gestion.apigestion.model.entite.Incident;
import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.model.entite.Status;


public interface IIncidentService extends IGettableService<Incident>, ICreatableService<Incident>, IEditableService<Incident>{
	public Incident getById(Long idMateriel, Long id);
	public List<Incident> getByMateriel(Long idMateriel);
	public List<Incident> getByStatus(Status status);
	public List<Incident> getByClientAndDates(Client client, Date dateDebut, Date dateFin);
	public List<Incident> getByClientAndDates(Long idClient, Date dateDebut, Date dateFin);
	public Incident cloreIncident(Long idMateriel, Long idIncident);
}
