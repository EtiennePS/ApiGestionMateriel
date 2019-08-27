package com.materiel.gestion.apigestion.service.impl;

import com.materiel.gestion.apigestion.exception.DeleteException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.materiel.gestion.apigestion.exception.CreationException;
import com.materiel.gestion.apigestion.exception.DataOwnerException;
import com.materiel.gestion.apigestion.model.entite.Client;
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
		
		if(c.getId() != null) {
			throw new CreationException("Il est interdit de renseigner l'id lors de la création de Personne");
		}
		
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
	@Transactional
	public Contact edit(Contact c) {
		
		Personne p;
		
		checkClient(c);
		
		//On autorise pas la modification du client, donc on écrase le client fournit
		c.setClient(clientService.getById(c.getClient().getId()));
		
		//On récupère la nouvelle fonction du contact grâce à l'id fournit, en ignorant le libellé fournit
		c.setFonction(fonctionService.getById(c.getFonction().getId()));

		// Si la personne lié au contact n'existe pas encore, il faut la créer, sinon on le modifie
		if(c.getPersonne().getId() == null) {
			p = personneService.create(c.getPersonne());
		}
		else {
			p = personneService.edit(c.getPersonne());
		}
		personneService.edit(c.getPersonne());
		c.setPersonne(personneService.getById(c.getPersonne().getId()));
		
		return repository.save(c);
	}

	@Override
	@Transactional
	public void delete(Contact c) {
		checkClient(c);
		repository.delete(c);

		if (repository.existsById(c.getId())){
			throw new DeleteException("Impossible de supprimer le contact");
		}
	}

	@Override
	public List<Contact> getByClient(Long idClient) {
		return repository.findByClient(clientService.getById(idClient));
	}
	
	@Override
	public Contact getById(Long idContact, Long idClient) {
		checkClient(createContact(idContact, idClient));
		return this.getById(idContact);
	}
	
	
	
	private void checkClient(Contact c){
        Contact contact = repository.getOne(c.getId());
        if (contact.getClient().getId()!= c.getClient().getId()){
            throw new DataOwnerException("Le contact n'appartient pas à ce client");
        }
    }
	
	private Contact createContact(Long idContact, Long idClient) {
		Client cl = new Client(idClient);
		Contact c = new Contact(idContact);
		c.setClient(cl);
		return c;
	}
	
}
