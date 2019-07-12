package com.materiel.gestion.apigestion.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.materiel.gestion.apigestion.service.IGenericService;

public abstract class GenericService<E> implements IGenericService<E> {
	@Autowired
	private JpaRepository<E, Long> repository;
	
	public E getById(Long id) {
		return this.repository.findById(id).get();
	}
	
	public List<E> getAll() {
		return this.repository.findAll();
	}
}
