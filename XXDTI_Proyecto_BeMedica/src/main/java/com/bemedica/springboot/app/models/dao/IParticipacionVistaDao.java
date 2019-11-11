package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.ParticipacionVista;

public interface IParticipacionVistaDao {

	public List<ParticipacionVista> findAll();
	
	public List<ParticipacionVista> findOneById(Long Id);

	
}
