package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.SolicitudOrden;

public interface ISolicitudOrdenDao {
	public void save(SolicitudOrden solicitud);
	
	public void delete(Long id);
	
	public SolicitudOrden findOne(Long solicitud);

	public List<SolicitudOrden> findAllSol();
}
