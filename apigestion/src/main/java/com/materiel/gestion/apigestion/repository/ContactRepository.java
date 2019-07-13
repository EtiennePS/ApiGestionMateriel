package com.materiel.gestion.apigestion.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.materiel.gestion.apigestion.model.entite.Contact;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
