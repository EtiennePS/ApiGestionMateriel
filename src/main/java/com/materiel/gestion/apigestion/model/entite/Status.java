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
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data @Entity
@Table(name="status")
public class Status {
	
	public Status() {}
	private Status(Long id) { setId(id);}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", length=11, nullable=false)
	private Long id;
	
	@Column(name="libelle", length=50, nullable=false)
	private String libelle;
	
	@JsonIgnore
	@OneToMany(mappedBy = "status")
	private List<Incident> incidents;
	
	public static final Status OUVERT = new Status(1l);
	public static final Status FERME = new Status(2l);
}
