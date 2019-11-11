package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.PaquetesCultivos;
import com.bemedica.springboot.app.models.entity.PaquetesCultivosQ;

public interface IPaquetesCultivosDao {

	public List<PaquetesCultivos> findAll();
	
	public void save (PaquetesCultivos paquetesCultivos);
	
	public PaquetesCultivos findOne (Long id );
	
	public void delete (Long id);
	
	public List<PaquetesCultivosQ> findAllById(Long id);
}
