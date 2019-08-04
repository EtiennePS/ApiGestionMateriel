package com.materiel.gestion.apigestion.service.impl;

import com.materiel.gestion.apigestion.model.entite.Ville;
import com.materiel.gestion.apigestion.repository.TypeAffectationRepository;
import com.materiel.gestion.apigestion.repository.VilleRepository;
import com.materiel.gestion.apigestion.service.IGettableService;
import com.materiel.gestion.apigestion.service.IVilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VilleService extends GettableService<Ville> implements IVilleService {
    @Autowired
    private VilleRepository repository;
    
    @Override
    public Ville create(Ville v) {
        return repository.save(v);
    }
}
