package com.materiel.gestion.apigestion.model.entite;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Data @Entity
@Table(name="ville")
public class Ville {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", length=11, nullable=false)
    private Long id;

    @Column(name = "codepostal",length = 5,nullable = false)
    private String codepostal;

    @Column(name = "ville",length = 100,nullable = false)
    private String ville;

}
