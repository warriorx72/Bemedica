package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Medicos;
import com.bemedica.springboot.app.models.entity.MedicosVista;

public interface IMedicosDao {

	public List<Medicos> findAll();
	
	public void save(Medicos medicos);
	
	public Medicos findOne(Long MedicoId);
	
	public void delete(Long MedicoId);
	
	public List<MedicosVista> mv();
	
}
 