package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Alertas;

public interface IAlertasDao {

	public List<Alertas> findAll();
	
	public void save(Alertas alertas);
	
	public Alertas findOne(Long AlertaId);
	
	public void delete(Long AlertaId);
}
