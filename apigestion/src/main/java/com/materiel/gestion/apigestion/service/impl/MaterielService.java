package com.materiel.gestion.apigestion.service.impl;

import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.repository.MaterielRepository;
import com.materiel.gestion.apigestion.repository.TypeAffectationRepository;
import com.materiel.gestion.apigestion.service.IMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MaterielService extends GenericService<Materiel> implements IMaterielService {
    @Autowired
    private MaterielRepository repository;
}
