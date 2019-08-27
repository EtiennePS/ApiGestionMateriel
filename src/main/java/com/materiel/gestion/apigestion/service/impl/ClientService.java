package com.materiel.gestion.apigestion.service.impl;

import com.materiel.gestion.apigestion.exception.DeleteException;
import com.materiel.gestion.apigestion.model.entite.Client;
import com.materiel.gestion.apigestion.model.entite.Ville;
import com.materiel.gestion.apigestion.repository.ClientRepository;
import com.materiel.gestion.apigestion.service.IClientService;
import com.materiel.gestion.apigestion.service.IVilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService extends GettableService<Client> implements IClientService {
	@Autowired
	private ClientRepository repository;

	@Autowired
	private IVilleService villeService;

	@Autowired
	private IClientService clientService;

	@Override
	@Transactional
	public Client create(Client c) {

		// Si la ville lié au client n'existe pas encore, il faut la créer
		Ville v = getOrCreateVille(c);
		
		c.setVille(v);
		return repository.save(c);
	}
	@Override
	public Client edit(Client c) {
		//On modifie la personne et on la réinjecte dans le contact
		c.setVille(getOrCreateVille(c));

		return repository.save(c);
	}
	@Override
	@Transactional
	public void delete(Client m) {
		repository.delete(m);
		if (repository.existsById(m.getId())){
			throw new DeleteException("Impossible de supprimer le Client");
		}
	}
	private Ville getOrCreateVille(Client c) {
		Ville v;
		if (c.getVille().getId() == null) {
			v = villeService.create(c.getVille());
		} else {
			v = villeService.getById(c.getVille().getId());
		}
		return v;
	}

}
