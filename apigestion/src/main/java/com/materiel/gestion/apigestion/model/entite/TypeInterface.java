package com.materiel.gestion.apigestion.model.entite;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data @Entity
@Table (name = "typeif")
public class TypeInterface {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Column(name="id", length=11, nullable=false)
	private Long id;
	
	@Column(name="libelle", length=10, nullable=false)
	private String libelle;
	
	@JsonIgnore
	@OneToMany(mappedBy="typeif")
	private List<Interface> interfaces;
	
}
