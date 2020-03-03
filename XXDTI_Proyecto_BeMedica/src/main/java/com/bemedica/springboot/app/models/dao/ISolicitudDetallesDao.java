package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.SolicitudDetalles;

public interface ISolicitudDetallesDao {
	public List<Object[]> findAllById(Long Sid);

	public SolicitudDetalles findOne(Long detallesId);

	public void save(SolicitudDetalles detalles);

	public void delete(Long id);
	
	;
}
