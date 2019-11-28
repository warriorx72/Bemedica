package com.bemedica.springboot.app.models.dao;

import java.util.List;

public interface ITicketDao {
	
	public List<Object[]> findPaciente(Long id);
	
	public List<Object[]> findEmpleado(Long id);

}
