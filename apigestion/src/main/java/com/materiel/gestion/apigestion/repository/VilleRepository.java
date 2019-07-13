package com.materiel.gestion.apigestion.repository;

import com.materiel.gestion.apigestion.model.entite.Ville;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VilleRepository extends JpaRepository<Ville, Long> {
}
