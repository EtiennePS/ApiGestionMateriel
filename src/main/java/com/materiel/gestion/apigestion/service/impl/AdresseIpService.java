package com.materiel.gestion.apigestion.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.exception.CreationException;
import com.materiel.gestion.apigestion.model.entite.AdresseIp;
import com.materiel.gestion.apigestion.model.entite.Contact;
import com.materiel.gestion.apigestion.model.entite.Interface;
import com.materiel.gestion.apigestion.repository.AdresseIpRepository;
import com.materiel.gestion.apigestion.service.IAdresseIpService;
import com.materiel.gestion.apigestion.service.IInterfaceService;
import com.materiel.gestion.apigestion.service.ITypeAffectationService;
import com.materiel.gestion.apigestion.service.ITypeInterfaceService;

@Service
public class AdresseIpService extends GettableService<AdresseIp> implements IAdresseIpService {
	
	@Autowired
	private AdresseIpRepository repository;
	
	@Autowired
	private ITypeAffectationService typeAffectationService;
	
	@Autowired 
	private IInterfaceService interfaceService;
	

@Override
@Transactional
public AdresseIp create(AdresseIp adr) {
    if (adr.getId() != null){
        throw new CreationException("Il est interdit de renseigner l'id lors de la création d'AdresseIp");
    }
    adr.setTypeAffectation(typeAffectationService.getById(adr.getTypeAffectation().getId()));
		
	 adr.setInterf(interfaceService.getById(adr.getInterf().getId()));
	return repository.save(adr);
 }

@Override
public AdresseIp getById(Long idInterf) {
	return repository.findByInterf(interfaceService.getById(idInterf));
}
}
