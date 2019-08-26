package com.materiel.gestion.apigestion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.materiel.gestion.apigestion.model.entite.Contact;
import com.materiel.gestion.apigestion.model.entite.Personne;
import com.materiel.gestion.apigestion.repository.ContactRepository;
import com.materiel.gestion.apigestion.service.IContactService;

@Service
public class ContactService extends GettableService<Contact> implements IContactService {
	
	@Autowired
	private ContactRepository repository;
	
	@Autowired
	private PersonneService personneService;
	
	@Autowired 
	private FonctionService fonctionService;
	
	@Autowired
	private ClientService clientService;

	@Override
	@Transactional
	public Contact create(Contact c) {
		Personne p;
		
		// Si la personne lié au contact n'existe pas encore, il faut la créer
		if(c.getPersonne().getId() == null) {
			p = personneService.create(c.getPersonne());
		}
		else {
			p = personneService.getById(c.getPersonne().getId());
		}
		
		// On récupère les données en base pour éviter que hibernate ne les modifie, et pour faire planter si elles n'existent pas
		c.setPersonne(p);
		c.setFonction(fonctionService.getById(c.getFonction().getId()));
		c.setClient(clientService.getById(c.getClient().getId()));
		
		return repository.save(c);
	}

	@Override
	public Contact edit(Contact c) {
		
		//On autorise pas la modification du client, donc on écrase le client fournit
		c.setClient(clientService.getById(c.getClient().getId()));
		
		//On récupère la nouvelle fonction du contact grâce à l'id fournit, en ignorant le libellé fournit
		c.setFonction(fonctionService.getById(c.getFonction().getId()));
		
		//On modifie la personne et on la réinjecte dans le contact
		personneService.edit(c.getPersonne());
		c.setPersonne(personneService.getById(c.getPersonne().getId()));
		
		return repository.save(c);
	}
	
}
