package com.materiel.gestion.apigestion.service.impl;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import com.materiel.gestion.apigestion.exception.NoSuchEntityException;
import com.materiel.gestion.apigestion.service.IGettableService;

public abstract class GettableService<E> implements IGettableService<E> {
	@Autowired
	private JpaRepository<E, Long> repository;
	
	public E getById(Long id) {
		try {
			return this.repository.findById(id).get();
		}
		catch (NoSuchElementException e) {
			throw new NoSuchEntityException("L'entité demandé n'existe pas", e);
		}
	}
	
	public List<E> getAll() {
		return this.repository.findAll();
	}
}
