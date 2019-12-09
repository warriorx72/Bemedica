package com.bemedica.springboot.app.models.dao;

import java.util.List;

public interface ITicketDao {
	
	public List<Object[]> findPaciente(Long id);
	
	public List<Object[]> findEmpleado(Long id);
	
	public List<Object[]> findFecha(Long id);
	
	public List<Object[]> findServ(Long id);
	
	public List<Object[]> findServAll();
	
	public List<Object[]> findTotal(Long id);

}
