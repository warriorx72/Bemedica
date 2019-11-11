package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.OrdenEstudio;

public interface IOrdenEstudioDao {
	public List<OrdenEstudio> findAll();

	public void save(OrdenEstudio ordenestudio);
	
	public OrdenEstudio findOne(Long id);
	
	public void delete(Long id);

}
