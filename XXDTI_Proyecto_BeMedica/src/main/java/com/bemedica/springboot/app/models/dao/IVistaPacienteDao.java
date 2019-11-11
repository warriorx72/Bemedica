package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.VistaPaciente;

public interface IVistaPacienteDao {

	public List<VistaPaciente> findAll();

	

}
