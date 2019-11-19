package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Convenio;

import com.bemedica.springboot.app.models.entity.ConvenioVista;

public interface IConvenio {
	
	public List<Convenio> findAll();
	public List <ConvenioVista> All();
	
	
	
	public void save (Convenio convenio);
	
	public Convenio findOne (Long id );
	
	public void delete (Long id);

}
