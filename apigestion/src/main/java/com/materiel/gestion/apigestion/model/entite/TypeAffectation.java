package com.materiel.gestion.apigestion.model.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data @Entity
@Table(name="typeaffectation")
public class TypeAffectation {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", length=11, nullable=false)
	private Long id;
	
	@Column(name="libelle", length=10, nullable=false)
	private String libelle;
}