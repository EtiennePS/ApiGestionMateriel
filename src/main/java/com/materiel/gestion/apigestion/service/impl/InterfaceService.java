package com.materiel.gestion.apigestion.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.exception.CreationException;
import com.materiel.gestion.apigestion.model.entite.Interface;
import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.model.entite.TypeInterface;
import com.materiel.gestion.apigestion.model.entite.TypeMateriel;
import com.materiel.gestion.apigestion.repository.InterfaceRepository;
import com.materiel.gestion.apigestion.repository.TypeInterfaceRepository;
import com.materiel.gestion.apigestion.service.IInterfaceService;
import com.materiel.gestion.apigestion.service.IMaterielService;
import com.materiel.gestion.apigestion.service.ITypeInterfaceService;

@Service
public class InterfaceService extends GettableService<Interface> implements IInterfaceService{

	@Autowired
	private InterfaceRepository repository;
	
	@Autowired
	private ITypeInterfaceService typeInterfaceService;


	  @Override
	    @Transactional
	    public Interface create(Interface i) {
	        if (i.getId() != null){
	            throw new CreationException("Interdiction de mettre un id customisé pour le materiel");
	        }
	        i.setTypeif(typeInterfaceService.getById(i.getTypeif().getId()));
			return repository.save(i);
		 }
	    }


