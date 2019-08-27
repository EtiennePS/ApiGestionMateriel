package com.materiel.gestion.apigestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.materiel.gestion.apigestion.model.entite.AdresseIp;
import com.materiel.gestion.apigestion.model.entite.Interface;

public interface AdresseIpRepository extends JpaRepository<AdresseIp, Long> {
	
	public AdresseIp findByInterf(Interface i);

}
