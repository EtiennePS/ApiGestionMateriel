package com.materiel.gestion.apigestion.service;

import java.util.List;

import com.materiel.gestion.apigestion.model.entite.Contact;

public interface IContactService extends IGettableService<Contact>, ICreatableService<Contact>, IEditableService<Contact>, IDeletableService<Contact> {

	public List<Contact> getByClient(Long idClient);
	public Contact getById(Long idContact, Long idClient);
}
