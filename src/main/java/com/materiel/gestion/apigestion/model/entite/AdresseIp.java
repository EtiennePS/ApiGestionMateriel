package com.materiel.gestion.apigestion.model.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data @Entity
@Table(name="adresseip")
public class AdresseIp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id", length=11, nullable=false)
	private Long id;

	@Column(name="ipV4", length=15, nullable=false)
	private String ipv4;

	@Column(name="ipV6", length=100, nullable=true)
	private String ipv6;

	@Column(name="masque", length=15, nullable=false)
	private String masque;

	@ManyToOne
	@JoinColumn(name = "idtypeaff", nullable = false)
	private TypeAffectation typeAffectation;
	
	@JsonIgnore
    @OneToOne
    @JoinColumn(name="idinterface")
    private Interface interf;
}
