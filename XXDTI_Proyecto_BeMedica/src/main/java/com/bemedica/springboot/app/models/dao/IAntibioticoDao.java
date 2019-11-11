package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Antibiotico;


public interface IAntibioticoDao {
	
public List<Antibiotico> findAll();
	
	public Antibiotico findOne(Long CatalogoId); 
	
	public void delete(Long CatalogoId);

	public void save(Antibiotico antibiotico);

}
