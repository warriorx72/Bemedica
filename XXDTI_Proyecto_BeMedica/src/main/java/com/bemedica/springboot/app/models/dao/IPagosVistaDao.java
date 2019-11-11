package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.PagosVista;

public interface IPagosVistaDao {
	
	public List<PagosVista> findAll();
	
	public PagosVista findOne(Long Id);

	public void delete(Long Id);

}
