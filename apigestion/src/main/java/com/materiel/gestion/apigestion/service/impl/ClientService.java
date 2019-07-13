package com.materiel.gestion.apigestion.service.impl;

import com.materiel.gestion.apigestion.model.entite.Client;
import com.materiel.gestion.apigestion.repository.ClientRepository;
import com.materiel.gestion.apigestion.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService extends GenericService<Client> implements IClientService {
@Autowired
private ClientRepository repository;
}
