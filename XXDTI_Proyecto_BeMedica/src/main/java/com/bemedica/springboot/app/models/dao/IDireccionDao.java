package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Direccion;

public interface IDireccionDao {

	public List<Direccion> findAll();

	public void save(Direccion direccion);
	
	public Direccion findOne(Long id);
	
	public void delete(Long id);

}
