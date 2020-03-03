package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.AppPaciente;

public interface IAppPacienteDao {
	public List<AppPaciente> findAll();

	public AppPaciente findOne(Long PacienteId);

	public void save(AppPaciente paciente);

	public void delete(Long id);

	String exist(Long id);

}
