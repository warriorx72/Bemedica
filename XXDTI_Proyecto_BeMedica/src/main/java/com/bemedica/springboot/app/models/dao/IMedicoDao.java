package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Medico;

public interface IMedicoDao {

	public List<Medico> findAll();

	public void save(Medico medico); ///cambiar medico a paciente sino funciona
	
	public Medico findOne(Long id);
	
	public void delete(Long id);

}
