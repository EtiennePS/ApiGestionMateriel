package com.materiel.gestion.apigestion.service.impl;

import com.materiel.gestion.apigestion.exception.CreationException;
import com.materiel.gestion.apigestion.exception.DeleteException;
import com.materiel.gestion.apigestion.model.entite.Client;
import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.model.entite.TypeMateriel;
import com.materiel.gestion.apigestion.repository.MaterielRepository;
import com.materiel.gestion.apigestion.service.IMaterielService;
import com.materiel.gestion.apigestion.service.impl.TypeMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class MaterielService extends GettableService<Materiel> implements IMaterielService {
    @Autowired
    private MaterielRepository repository;
    @Autowired
    TypeMaterielService typeMaterielService;
    @Autowired
    ClientService clientService;


    @Override
    @Transactional
    public Materiel create(Materiel m) {
        TypeMateriel t;
        if (m.getId() != null){
            throw new CreationException("Interdiction de mettre un id customisé pour le materiel");
        }
        if (m.getTypeMateriel().getId() ==null){
            t = typeMaterielService.create(m.getTypeMateriel());
        }
        else{
        	t=typeMaterielService.getById(m.getTypeMateriel().getId());
        }
	    m.setTypeMateriel(t);
	    m.setClient(clientService.getById(m.getClient().getId()));
	    return repository.save(m);
    }

    @Override
    @Transactional
    public void delete(Materiel m) {
        repository.delete(m);
        if (repository.existsById(m.getId())){
            throw new DeleteException("Impossible de supprimer le materiel");
        }
    }
}