package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Factura;
import com.bemedica.springboot.app.models.entity.FacturaVista;

public interface IFacturaVistaDao {

	public List<FacturaVista> findAll();
	
	public void save(Factura FacturaId);

	public Factura findOne(Long FacturaId);

	public void delete(Long FacturaId);
}
