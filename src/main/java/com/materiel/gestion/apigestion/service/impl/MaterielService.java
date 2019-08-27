package com.materiel.gestion.apigestion.service.impl;

import com.materiel.gestion.apigestion.exception.CreationException;
import com.materiel.gestion.apigestion.exception.DataOwnerException;
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
import java.sql.DataTruncation;
import java.util.List;

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
    public Materiel edit(Materiel m ){
        checkClient(m);
        m.setClient(clientService.getById(m.getClient().getId()));
        m.setTypeMateriel(typeMaterielService.getById(m.getTypeMateriel().getId()));
        return repository.save(m);

    }

    @Override
    @Transactional
    public void delete(Materiel m) {
        checkClient(m);
        repository.delete(m);
        if (repository.existsById(m.getId())){
            throw new DeleteException("Impossible de supprimer le materiel");
        }
    }
    @Override
    public List<Materiel> getByClient(Long idClient) {
        return repository.findByClient(clientService.getById(idClient));
    }

    @Override
    public Materiel getById(Long idMateriel, Long idClient) {
        checkClient(createMateriel(idMateriel, idClient));
        return this.getById(idMateriel);
    }

    private void checkClient(Materiel m){
        Materiel materiel = repository.getOne(m.getId());
        if (materiel.getClient().getId()!= m.getClient().getId()){
            throw new DataOwnerException("Le materiel n'appartient pas à ce client");
        }
    }
    private Materiel createMateriel(Long idMateriel, Long idClient) {
        Client m1 = new Client(idClient);
        Materiel m = new Materiel(idMateriel);
        m.setClient(m1);
        return m;
    }
}