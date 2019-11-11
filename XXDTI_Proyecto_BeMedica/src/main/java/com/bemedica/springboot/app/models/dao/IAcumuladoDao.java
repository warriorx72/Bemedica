package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Acumulado;
import com.bemedica.springboot.app.models.entity.MedicoPago;

public interface IAcumuladoDao {

	public List<Acumulado> findAll();
	
	public void save(MedicoPago MedicoId);
	
	public MedicoPago findOne(int Id);

	public void delete(int Id);
}
