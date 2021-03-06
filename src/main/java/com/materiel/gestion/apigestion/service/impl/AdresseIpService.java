package com.materiel.gestion.apigestion.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.exception.CreationException;
import com.materiel.gestion.apigestion.exception.DataOwnerException;
import com.materiel.gestion.apigestion.exception.DeleteException;
import com.materiel.gestion.apigestion.model.entite.AdresseIp;
import com.materiel.gestion.apigestion.model.entite.Interface;
import com.materiel.gestion.apigestion.repository.AdresseIpRepository;
import com.materiel.gestion.apigestion.service.IAdresseIpService;
import com.materiel.gestion.apigestion.service.IInterfaceService;
import com.materiel.gestion.apigestion.service.ITypeAffectationService;

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

@Override
@Transactional
public AdresseIp edit(AdresseIp adr) { 
	Interface interf = interfaceService.getById(adr.getInterf().getId());
	adr.setId(getAdresseIpId(interf));
	adr.setInterf(interf);
	adr.setTypeAffectation(typeAffectationService.getById(adr.getTypeAffectation().getId()));
	
	return repository.save(adr);
}


@Override
@Transactional
public void delete(AdresseIp adr) {
	Interface interf = interfaceService.getById(adr.getInterf().getId());
	adr.setId(getAdresseIpId(interf));
	repository.delete(adr);

	if (repository.existsById(adr.getId())){
		throw new DeleteException("Impossible de supprimer l'adresse ip");
	}
}

private Long getAdresseIpId(Interface interf) {
	if (interf.getAdresse() == null){
		throw new DataOwnerException("l'interface n'a pas d'adresse ip");
	}
	return interf.getAdresse().getId();
}
	
}
