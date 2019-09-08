package com.materiel.gestion.apigestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.materiel.gestion.apigestion.model.entite.Personne;

@Repository
public interface PersonneRepository extends JpaRepository<Personne, Long> {

}
