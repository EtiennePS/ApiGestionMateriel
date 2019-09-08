package com.materiel.gestion.apigestion.repository;

import com.materiel.gestion.apigestion.model.entite.TypeMateriel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeMaterielRepository extends JpaRepository<TypeMateriel,Long> {

}
