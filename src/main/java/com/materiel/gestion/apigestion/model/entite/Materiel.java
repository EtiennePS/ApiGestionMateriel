package com.materiel.gestion.apigestion.model.entite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

import javax.persistence.*;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data
@Entity
@Table(name="materiel")
public class Materiel {
	
	public Materiel() { }
	public Materiel(Long id) { setId(id); }
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", length=11, nullable=false)
    private Long id;

    @Column(name = "libelle",length = 100,nullable = false)
    private String libelle;

    @Column(name = "numserie",length = 30,nullable = false)
    private String numSerie;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "idclient", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "idtype", nullable = false)
    private TypeMateriel typeMateriel;

	@OneToMany(mappedBy="materiel")
	private List<Interface> interfaces;
	
	@OneToMany(mappedBy = "materiel")
	private List<Incident> incidents;
}
