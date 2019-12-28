package com.materiel.gestion.apigestion.model.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data @Entity
@Table(name="appartient")
public class Contact {
	
	public Contact() { }
	public Contact(Long id) { setId(id); }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", length=11, nullable=false)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="idfonction", nullable=false)
	private Fonction fonction;
	
	@ManyToOne
	@JoinColumn(name="idpersonne")
	private Personne personne;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idclient")
	private Client client;
}
