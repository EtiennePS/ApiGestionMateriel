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
@Table(name="personne")
public class Personne {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", length=11, nullable=false)
	private Long id;
	
	@Column(name="nom", length=50, nullable=false)
	private String nom;
	
	@Column(name="prenom", length=50, nullable=false)
	private String prenom;
	
	@Column(name="telephone", length=14, nullable=true)
	private String telephone;
	
	@Column(name="email", length=100, nullable=false)
	private String email;
	
	@JsonIgnore
	@OneToMany(mappedBy="personne")
	private List<Contact> contacts;
}
