package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.PpSucursal;

import com.bemedica.springboot.app.models.entity.PpSucursalVista;

public interface IPpSucursal {
	
	public List<PpSucursal> findAll();
	
	public List<PpSucursalVista> ppsv(Long id);
	
	public void save (PpSucursal pps);
	
	public PpSucursal findOne (Long id );
	
	public void delete (Long id);

}
