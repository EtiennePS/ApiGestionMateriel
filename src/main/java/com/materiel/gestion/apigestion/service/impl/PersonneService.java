package com.materiel.gestion.apigestion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.exception.CreationException;
import com.materiel.gestion.apigestion.exception.EditionException;
import com.materiel.gestion.apigestion.model.entite.Personne;
import com.materiel.gestion.apigestion.repository.PersonneRepository;
import com.materiel.gestion.apigestion.service.IPersonneService;

@Service
public class PersonneService extends GettableService<Personne> implements IPersonneService {
	
	@Autowired
	private PersonneRepository repository;

	@Override
	public Personne create(Personne p) {
		if(p.getId() != null) {
			throw new CreationException("Il est interdit de renseigner l'id lors de la création de Personne");
		}
		return repository.save(p);
	}

	@Override
	public Personne edit(Personne p) {
		//On vérifie que l'id soit non null et existante
		if(p.getId() == null) {
			throw new EditionException("Impossible  de modifier une Personne sans id.");
		}
		else {
			this.getById(p.getId());
		}
		
		return repository.save(p);
	}
	
	
}
