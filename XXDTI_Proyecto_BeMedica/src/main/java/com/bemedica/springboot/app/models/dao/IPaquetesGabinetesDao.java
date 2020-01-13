package com.bemedica.springboot.app.models.dao;

import java.util.List;
import com.bemedica.springboot.app.models.entity.PaquetesGabinetes;


public interface IPaquetesGabinetesDao {
	
	public List<PaquetesGabinetes> findAll();
	
	public void save (PaquetesGabinetes paquetesGabinetes);
	
	public PaquetesGabinetes findOne (Long id );
	
	public void delete (Long id);
	
	public List<Object[]> findAllById(Long id);

}
