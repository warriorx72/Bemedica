package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.OrdenE;

public interface IOrdenDaoE {
	public List<OrdenE> findAll();

	public void save(OrdenE orden);
	
	public OrdenE findOne(Long id);
	
	public void delete(Long id);

}
