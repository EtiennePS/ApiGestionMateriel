package com.materiel.gestion.apigestion.service.impl;

import com.materiel.gestion.apigestion.model.entite.Ville;
import com.materiel.gestion.apigestion.repository.TypeAffectationRepository;
import com.materiel.gestion.apigestion.repository.VilleRepository;
import com.materiel.gestion.apigestion.service.IGenericService;
import com.materiel.gestion.apigestion.service.IVilleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VilleService extends GenericService<Ville> implements IVilleService {
    @Autowired
    private VilleRepository repository;
}
