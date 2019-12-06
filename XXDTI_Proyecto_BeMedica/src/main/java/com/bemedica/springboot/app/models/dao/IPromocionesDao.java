package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Promociones;

public interface IPromocionesDao {

	public List<Promociones> findAll();
	
	public void save (Promociones promociones);
	
	public Promociones findOne (Long id );
	
	public void delete (Long id);
}
