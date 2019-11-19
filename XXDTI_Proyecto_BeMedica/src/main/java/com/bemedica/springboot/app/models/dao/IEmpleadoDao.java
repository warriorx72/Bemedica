package com.bemedica.springboot.app.models.dao;


import java.util.List;
import com.bemedica.springboot.app.models.entity.Empleado;



public interface IEmpleadoDao {
	
	public List<Empleado> findAll();
	
	public void save (Empleado empleado);
	
	public Empleado findOne (Long empleado_id );
	
	public void delete (Long empleado_id);

}