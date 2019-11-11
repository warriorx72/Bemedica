package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.MedicoPago;

public interface IMedicoPagoDao {

	public List<MedicoPago> findAll();

	public void save(MedicoPago medicoId);
	
	public MedicoPago findOne(Long medicoId);
	
	public void delete(Long mpId);
}
