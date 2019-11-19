package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.OrdenEstudioE;

public interface IOrdenEstudioDaoE {
	
	
	public List<OrdenEstudioE> findAll();

	public void save(OrdenEstudioE ordenestudio);
	
	public OrdenEstudioE findOne(Long id);
	
	public void delete(Long id);

}
