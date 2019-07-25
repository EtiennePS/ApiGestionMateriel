package com.materiel.gestion.apigestion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.materiel.gestion.apigestion.model.entite.TypeInterface;
import com.materiel.gestion.apigestion.repository.TypeInterfaceRepository;

import com.materiel.gestion.apigestion.service.ITypeInterfaceService;

@Service
public class TypeIntrfaceService extends GenericService<TypeInterface> implements ITypeInterfaceService{

		@Autowired
		private TypeInterfaceRepository repository;
	}
