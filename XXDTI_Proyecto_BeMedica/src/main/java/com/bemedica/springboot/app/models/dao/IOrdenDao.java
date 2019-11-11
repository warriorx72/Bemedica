package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Orden;

public interface IOrdenDao {
	public List<Orden> findAll();

	public void save(Orden orden);
	
	public Orden findOne(Long id);
	
	public void delete(Long id);

}
