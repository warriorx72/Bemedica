package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.PaquetesPerfiles;

public interface IPaquetesPerfiles {
	
	
	public List<PaquetesPerfiles> findAll();
	
	public void save (PaquetesPerfiles paquetespromociones);
	
	public PaquetesPerfiles findOne (Long id );
	
	public void delete (Long id);

}
