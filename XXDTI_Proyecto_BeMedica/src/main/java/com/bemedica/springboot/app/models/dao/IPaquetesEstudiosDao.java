package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.EstudiosListQ;
import com.bemedica.springboot.app.models.entity.PaquetesEstudios;

public interface IPaquetesEstudiosDao {
	public List<PaquetesEstudios> findAll();
	
	public void save (PaquetesEstudios paquetesEstudios);
	
	public PaquetesEstudios findOne (Long id );
	
	public void delete (Long id);
	
	public List<Object> findAllById(Long id);
	
	public List<EstudiosListQ> findEstudios(Long id);
}
