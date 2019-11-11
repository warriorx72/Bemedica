package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Factura;

public interface IFacturaDao {

	public List<Factura> findAll();

	public void save(Factura Factura);
	
	public Factura findOne(Long FacturaId);
	
	public void delete(Long FacturaId);
	
}
