package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.EstudioE;

public interface IEstudio {
	
	public List<EstudioE> findAll();
	
	public void save (EstudioE estudio);
	
	public EstudioE findOne (Long id );
	
	public void delete (Long id);
	
	public List<Object[]> select(Long id );
	
	
	
	
}
