package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.PaquetesPerfiles;
import com.bemedica.springboot.app.models.entity.PaquetesPerfilesQ;
import com.bemedica.springboot.app.models.entity.PerfilesListQ;

public interface IPaquetesPerfilesDao {

	public List<PaquetesPerfiles> findAll();
	
	public void save (PaquetesPerfiles paquetesPerfiles);
	
	public PaquetesPerfiles findOne (Long id );
	
	public void delete (Long id);
	
	public List<PaquetesPerfilesQ> findAllById(Long id);
	
	public List<PerfilesListQ> findPerfiles(Long id);
	
	
	
}
