package com.materiel.gestion.apigestion.model.entite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

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

    @ManyToOne
    @JoinColumn(name = "idclient", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "idtype", nullable = false)
    private TypeMateriel typeMateriel;
}
