package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Empresa;

public interface IEmpresa {
	
	public List<Empresa> findAll();
	
	public void save (Empresa empresa);
	
	public Empresa findOne (Long id );
	
	public void delete (Long id);

}
