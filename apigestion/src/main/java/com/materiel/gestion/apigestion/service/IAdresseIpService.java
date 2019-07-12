package com.materiel.gestion.apigestion.service;

import java.util.List;

import com.materiel.gestion.apigestion.model.entite.AdresseIp;;

public interface IAdresseIpService {
	public AdresseIp getById(Long id);
	
	public List<AdresseIp> getAll();
}
