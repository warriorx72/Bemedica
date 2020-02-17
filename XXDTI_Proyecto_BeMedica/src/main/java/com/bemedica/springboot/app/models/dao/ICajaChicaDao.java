package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.CajaChica;

public interface ICajaChicaDao {
	
	public List<CajaChica> findAll();	
	
	public void save (CajaChica cajaChica);
	
	public CajaChica findOne (Long id );
	
	public void delete (Long id);

}
