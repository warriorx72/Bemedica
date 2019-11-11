package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Paquetes;

public interface IPaquetesDao {

	public List<Paquetes> findAll();
	
	public void save (Paquetes paquetes);
	
	public Paquetes findOne (Long id );
	
	public void delete (Long id);
	
	public int findLastId();
}
