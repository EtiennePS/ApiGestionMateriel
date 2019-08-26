package com.materiel.gestion.apigestion.service.impl;

import com.materiel.gestion.apigestion.model.entite.TypeMateriel;
import com.materiel.gestion.apigestion.repository.TypeAffectationRepository;
import com.materiel.gestion.apigestion.repository.TypeMaterielRepository;
import com.materiel.gestion.apigestion.service.ITypeMaterielService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TypeMaterielService extends GettableService<TypeMateriel> implements ITypeMaterielService {
    @Autowired
    private TypeMaterielRepository repository;

    @Override
    public TypeMateriel create(TypeMateriel p) {
        return repository.save(p);
    }
}
