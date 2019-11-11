package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Pacientes;
import com.bemedica.springboot.app.models.entity.PacienteVista;

public interface IPacientesDao {
	
	public List<Pacientes> findAll();
	
	public void save(Pacientes pacientes);
	
	public Pacientes findOne(Long PacienteId);
	
	public void delete(Long PacienteId);
	
	public List<PacienteVista> pv();

}
