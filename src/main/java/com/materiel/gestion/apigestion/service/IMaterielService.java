package com.materiel.gestion.apigestion.service;

import com.materiel.gestion.apigestion.model.entite.Materiel;

import java.util.List;

public interface IMaterielService extends IGettableService<Materiel>, ICreatableService<Materiel>, IEditableService<Materiel>, IDeletableService<Materiel> {
    public List<Materiel> getByClient(Long idClient);
    public Materiel getById(Long idMateriel, Long idClient);
    public byte[] getQrCodeById(Long idMateriel, Long idClient);
}
