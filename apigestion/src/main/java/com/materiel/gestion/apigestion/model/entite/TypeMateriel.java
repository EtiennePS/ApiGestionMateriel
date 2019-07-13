package com.materiel.gestion.apigestion.model.entite;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="typemateriel")
public class TypeMateriel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", length=11, nullable=false)
    private Long id;

    @Column(name = "libelle",length = 50,nullable = false)
    private String libelle;

}
