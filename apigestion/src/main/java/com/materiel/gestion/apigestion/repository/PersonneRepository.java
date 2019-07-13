package com.materiel.gestion.apigestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.materiel.gestion.apigestion.model.entite.Personne;

public interface PersonneRepository extends JpaRepository<Personne, Long> {

}
