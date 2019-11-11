package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Estudio;

public interface IEstudioDao {

	public List<Estudio> findAll();

	public void save(Estudio estudio);
	
	public Estudio findOne(Long id);
	
	public void delete(Long id);

}
