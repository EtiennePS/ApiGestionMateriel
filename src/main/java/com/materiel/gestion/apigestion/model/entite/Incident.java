package com.materiel.gestion.apigestion.model.entite;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data @Entity
@Table(name="incident")
public class Incident {
	
	public Incident() {}
	public Incident(Long id) { setId(id); }
	public Incident(Long id, Materiel m) { setId(id); setMateriel(m); }
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", length=11, nullable=false)
	private Long id;
	
	@Column(name="libelle", length=50, nullable=false)
	private String libelle;
	
	@Column(name="symptome", length=1000, nullable=false)
	private String symptome;
	
	@Column(name="resolution", length=1000, nullable=true)
	private String resolution;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datecreation", length=1000, nullable=false)
	private Date dateCreation;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="datecloture", length=1000, nullable=true)
	private Date dateCloture;
	
	@ManyToOne
	@JoinColumn(name = "idstatus", nullable = false)
	private Status status;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "idmateriel", nullable = false)
	private Materiel materiel;
	
}
