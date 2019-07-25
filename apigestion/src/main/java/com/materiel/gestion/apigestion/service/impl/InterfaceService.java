package com.materiel.gestion.apigestion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.model.entite.Interface;
import com.materiel.gestion.apigestion.repository.InterfaceRepository;
import com.materiel.gestion.apigestion.service.IInterfaceService;

@Service
public class InterfaceService extends GenericService<Interface> implements IInterfaceService{

		@Autowired
		private InterfaceRepository repository;
	}
