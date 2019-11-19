package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.EmpleadoVista;
import com.bemedica.springboot.app.models.entity.EmpleadosSucursal;

public interface IEmpleadosSucursal {
	
	public List<EmpleadosSucursal> findAll();
	
	public List <EmpleadoVista> All();
	
	public List <EmpleadoVista> filtrado(String filtro);
	
	public void save (EmpleadosSucursal EmpleadosSucursal);
	
	public EmpleadosSucursal findOne (Long id );
	
	public void delete (Long id);

}
