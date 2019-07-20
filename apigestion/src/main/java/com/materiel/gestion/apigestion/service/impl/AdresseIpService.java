package com.materiel.gestion.apigestion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.model.entite.AdresseIp;
import com.materiel.gestion.apigestion.repository.AdresseIpRepository;
import com.materiel.gestion.apigestion.service.IAdresseIpService;

@Service
public class AdresseIpService extends GettableService<AdresseIp> implements IAdresseIpService {
	
	@Autowired
	private AdresseIpRepository repository;
	
}
