package com.materiel.gestion.apigestion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.model.entite.Status;
import com.materiel.gestion.apigestion.repository.StatusRepository;
import com.materiel.gestion.apigestion.service.IStatusService;

@Service
public class StatusService extends GettableService<Status> implements IStatusService {
	
	@SuppressWarnings("unused")
	@Autowired
	private StatusRepository repository;
}
