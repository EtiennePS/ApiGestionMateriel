package com.materiel.gestion.apigestion.service.impl;

import com.materiel.gestion.apigestion.model.entite.Client;
import com.materiel.gestion.apigestion.model.entite.Ville;
import com.materiel.gestion.apigestion.repository.ClientRepository;
import com.materiel.gestion.apigestion.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService extends GettableService<Client> implements IClientService {
	@Autowired
	private ClientRepository repository;

	@Autowired
	private VilleService villeService;

	@Override
	@Transactional
	public Client create(Client c) {
		Ville v;

		// Si la ville lié au client n'existe pas encore, il faut la créer
		if (c.getVille().getId() == null) {
			v = villeService.create(c.getVille());
		} else {
			v = villeService.getById(c.getVille().getId());
		}
		
		c.setVille(v);
		return repository.save(c);
	}

}
