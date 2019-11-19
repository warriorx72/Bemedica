package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.PpEstudios;
import com.bemedica.springboot.app.models.entity.PpEstudioVista;

public interface IPpEstudios {

	
	public List<PpEstudios> findAll();
	
	
	public List<PpEstudioVista> ppev(Long id);
	
	public void save (PpEstudios ppe);
	
	public PpEstudios findOne (Long id );
	
	public void delete (Long id);
	
}
