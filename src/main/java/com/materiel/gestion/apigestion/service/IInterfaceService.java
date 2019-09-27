package com.materiel.gestion.apigestion.service;

import java.util.List;

import com.materiel.gestion.apigestion.model.entite.Interface;

public interface IInterfaceService extends IGettableService<Interface>, ICreatableService<Interface>, IEditableService<Interface>, IDeletableService<Interface> {
	public List<Interface> getByMateriel(Long idMateriel);
	public Interface getById(Long idMateriel, Long id);
}
