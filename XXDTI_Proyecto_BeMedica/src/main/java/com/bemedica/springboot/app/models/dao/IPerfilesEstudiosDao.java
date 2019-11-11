package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.EstudiosListQ;
import com.bemedica.springboot.app.models.entity.PerfilesEstudios;
import com.bemedica.springboot.app.models.entity.PerfilesEstudiosView;


public interface IPerfilesEstudiosDao {
	
	public List<PerfilesEstudios> findAll();
	
	public void save (PerfilesEstudios perfilesEstudios);
	
	public PerfilesEstudios findOne (Long id );
	
	public void delete (Long id);
	
	public List<PerfilesEstudiosView> findAllById(Long id);
	
	public List<EstudiosListQ> findEstudios(Long id);

}
