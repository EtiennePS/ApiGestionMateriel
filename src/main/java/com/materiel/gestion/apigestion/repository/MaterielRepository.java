package com.materiel.gestion.apigestion.repository;

import com.materiel.gestion.apigestion.model.entite.Client;
import com.materiel.gestion.apigestion.model.entite.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterielRepository extends JpaRepository<Materiel,Long> {
    public List<Materiel> findByClient(Client C);
}
