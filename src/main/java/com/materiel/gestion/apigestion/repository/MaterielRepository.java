package com.materiel.gestion.apigestion.repository;

import com.materiel.gestion.apigestion.model.entite.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterielRepository extends JpaRepository<Materiel,Long> {
}
