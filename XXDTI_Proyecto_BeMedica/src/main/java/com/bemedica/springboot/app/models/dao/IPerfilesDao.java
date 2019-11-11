package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Perfiles;



public interface IPerfilesDao {
	
	public List<Perfiles> findAll();
	
	public void save (Perfiles perfiles);
	
	public Perfiles findOne (Long id );
	
	public void delete (Long id);
	

}
