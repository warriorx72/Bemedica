package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.ConvenioPaquete;

public interface IConvenioPaqueteDao {
	
	public List<ConvenioPaquete> findAll();
	
	public void save (ConvenioPaquete copa);
	
	public ConvenioPaquete findOne (Long id );
	
	public void delete (Long id);
	
	public List<Object[]> findPaquete(Long id);
	
	public List<Object[]> findPaqueteConvenio(Long id);
}
