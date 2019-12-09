package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Sucursal;
import com.bemedica.springboot.app.models.entity.SucursalE;

public interface ISucursal {
	
	public List<SucursalE> findAll();
	
	public void save (SucursalE sucursal);
	
	public SucursalE findOne (Long id );
	
	public void delete (Long id);
	
	public List <Object []> listarSucursales();

}
