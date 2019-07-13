package com.materiel.gestion.apigestion.model.entite;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", length=11, nullable=false)
    private Long id;

    @Column(name = "nom",length = 50,nullable = false)
    private String nom;

    @Column(name = "adresse1",length = 250,nullable = true)
    private String adresse1;

    @Column(name = "adresse2",length = 250,nullable = true)
    private String adresse2;

    @ManyToOne
    @JoinColumn(name = "ville", nullable = true)
    private TypeAffectation typeAffectation;
}


