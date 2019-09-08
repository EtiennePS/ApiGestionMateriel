package com.materiel.gestion.apigestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.materiel.gestion.apigestion.model.entite.Status;

public interface StatusRepository extends JpaRepository<Status, Long> {

}
