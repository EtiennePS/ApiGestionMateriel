package com.materiel.gestion.apigestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.materiel.gestion.apigestion.model.entite.Interface;

@Repository
public interface InterfaceRepository extends JpaRepository<Interface, Long> {

}
