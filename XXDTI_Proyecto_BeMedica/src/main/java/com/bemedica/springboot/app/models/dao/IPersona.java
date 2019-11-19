package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.PersonaE;

public interface IPersona {
	
	public List<PersonaE> findAll();
	
	public void save (PersonaE persona);
	
	public PersonaE findOne (Long id );
	
	public void delete (Long id);
	
	

}
