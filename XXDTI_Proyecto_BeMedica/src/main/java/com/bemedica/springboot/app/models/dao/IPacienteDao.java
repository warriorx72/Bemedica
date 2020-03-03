package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Paciente;

public interface IPacienteDao {

	public List<Paciente> findAll();

	public void save(Paciente paciente);  //CAMBIAR BOOLEAN A VOID
	
	public Paciente findOne(Long id);
	
	public void delete(Long id);
	
	public Object findByPersona (Long id);

}
