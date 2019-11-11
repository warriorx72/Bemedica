package com.bemedica.springboot.app.models.dao;

import java.util.List;


import com.bemedica.springboot.app.models.entity.Estudios;
import com.bemedica.springboot.app.models.entity.EstudiosView;

public interface IEstudiosDao {

	public List<Estudios> findAll();
	
	public void save (Estudios estudios);
	
	public Estudios findOne (Long id );
	
	public void delete (Long id);
	
	public int findLastId();
	
	public List<EstudiosView> findAllEstudios();
}
