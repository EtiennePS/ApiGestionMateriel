package com.materiel.gestion.apigestion.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.materiel.gestion.apigestion.model.entite.Interface;
import com.materiel.gestion.apigestion.model.entite.Materiel;

@Repository
public interface InterfaceRepository extends JpaRepository<Interface, Long> {
	public List<Interface> findByMateriel(Materiel m);
}
