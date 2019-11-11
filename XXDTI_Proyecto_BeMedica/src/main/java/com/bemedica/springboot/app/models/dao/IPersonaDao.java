package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Persona;

public interface IPersonaDao {

	public List<Persona> findAll();

	public void save(Persona persona);
	
	public Persona findOne(Long id); ///corregir to long
	
	public void delete(Long id); //7corregir to long

}
