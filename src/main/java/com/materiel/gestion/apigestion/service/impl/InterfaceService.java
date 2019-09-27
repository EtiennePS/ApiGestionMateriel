package com.materiel.gestion.apigestion.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.exception.CreationException;
import com.materiel.gestion.apigestion.exception.DataOwnerException;
import com.materiel.gestion.apigestion.exception.DeleteException;
import com.materiel.gestion.apigestion.exception.EditionException;
import com.materiel.gestion.apigestion.model.entite.AdresseIp;
import com.materiel.gestion.apigestion.model.entite.Interface;
import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.repository.InterfaceRepository;
import com.materiel.gestion.apigestion.service.IAdresseIpService;
import com.materiel.gestion.apigestion.service.IInterfaceService;
import com.materiel.gestion.apigestion.service.IMaterielService;
import com.materiel.gestion.apigestion.service.ITypeInterfaceService;

@Service
public class InterfaceService extends GettableService<Interface> implements IInterfaceService{

	@Autowired
	private InterfaceRepository repository;
	
	@Autowired
	private ITypeInterfaceService typeInterfaceService;
	
	@Autowired 
	private IMaterielService materielService;
	
	@Autowired
	private IAdresseIpService adresseIpService;

	@Override
    @Transactional
    public Interface create(Interface i) {
        if (i.getId() != null){
            throw new CreationException("Interdiction de mettre un id customisé pour le materiel");
        }
        
        i.setTypeif(typeInterfaceService.getById(i.getTypeif().getId()));
        i = repository.save(i);
        
        if(i.getAdresse() != null) {
        	AdresseIp a = i.getAdresse();
        	a.setInterf(i);
        	i.setAdresse(adresseIpService.create(a));
        }
        
		return i;
	 }
	
	@Override
	@Transactional
	public Interface edit(Interface i) { 
		checkMateriel(i);
		if(i.getId() == null) {
			throw new EditionException("Impossible de modifier une Interface sans id.");
		}
		
		Interface interf = this.getById(i.getId());
		
		if(i.getAdresse() != null) {
			AdresseIp a = i.getAdresse();
			a.setInterf(i);
			if(interf.getAdresse() == null) {
				i.setAdresse(this.adresseIpService.create(a));
			}
			else {
				a.setId(interf.getAdresse().getId());
				i.setAdresse(this.adresseIpService.edit(a));
			}
		}
		
		return repository.save(i);
	}
	
	@Override
	@Transactional
	public void delete(Interface i) {
		checkMateriel(i);
		repository.delete(i);
		
		if (repository.existsById(i.getId())){
			throw new DeleteException("Impossible de supprimer l'interface");
		}
	}
		
	private void checkMateriel(Interface i){
        Interface interf = repository.getOne(i.getId());
        if (interf.getMateriel().getId() != i.getMateriel().getId()){
            throw new DataOwnerException("L'interface n'appartient pas à ce materiel");
        }
	}

	@Override
	public List<Interface> getByMateriel(Long idMateriel) {
		Materiel m = materielService.getById(idMateriel);
		return this.repository.findByMateriel(m);
	}
	
	@Override
	public Interface getById(Long idMateriel, Long id) {
		checkMateriel(new Interface(id, new Materiel(idMateriel)));
		return getById(id);
	}
}



