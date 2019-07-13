package com.materiel.gestion.apigestion.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.materiel.gestion.apigestion.model.entite.Contact;
import com.materiel.gestion.apigestion.repository.ContactRepository;
import com.materiel.gestion.apigestion.service.IContactService;

@Service
public class ContactService extends GenericService<Contact> implements IContactService {
	
	@Autowired
	private ContactRepository repository;
	
}
