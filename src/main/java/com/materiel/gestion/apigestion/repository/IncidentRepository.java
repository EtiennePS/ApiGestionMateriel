package com.materiel.gestion.apigestion.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.materiel.gestion.apigestion.model.entite.Incident;
import com.materiel.gestion.apigestion.model.entite.Materiel;
import com.materiel.gestion.apigestion.model.entite.Status;

@Repository
public interface IncidentRepository extends JpaRepository<Incident, Long> {
	
	@Query(	"select i " + 
			"from Incident i " + 
			"inner join Materiel m on m.id = i.materiel.id " + 
			"inner join Client c on m.client.id = c.id " + 
			"where " + 
				"(i.dateCloture is null or i.dateCloture >= :dateDebut ) " + 
				"and i.dateCreation <= :dateFin " + 
				"and c.id = :idClient ")
	public List<Incident> getAllByClientBetween(Date dateDebut, Date dateFin, Long idClient);
	
	public List<Incident> findByMateriel(Materiel m);
	public List<Incident> findByStatus(Status s);
}
