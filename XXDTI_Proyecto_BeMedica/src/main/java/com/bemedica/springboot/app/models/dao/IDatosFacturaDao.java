package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.DatosFactura;

public interface IDatosFacturaDao {
	
	public List<DatosFactura> findAll();
	
	public void save (DatosFactura datosfactura);
	
	public DatosFactura findOne(Long DatosFacturaId);
	
	public void delete(Long DatosFacturaId);

}
