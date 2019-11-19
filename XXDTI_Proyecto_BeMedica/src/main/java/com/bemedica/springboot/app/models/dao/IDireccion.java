package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.DireccionE;


public interface IDireccion {
	
	public List<DireccionE> findAll();
	
	public void save (DireccionE direccion);
	
	public DireccionE findOne (Long id );
	
	public void delete (Long id);
	
	

}
