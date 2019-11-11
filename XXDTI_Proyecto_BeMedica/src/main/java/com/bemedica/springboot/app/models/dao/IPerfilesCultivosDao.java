package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.PerfilesCultivos;
import com.bemedica.springboot.app.models.entity.PerfilesCultivosQ;

public interface IPerfilesCultivosDao {

	public List<PerfilesCultivos> findAll();
	
	public void save (PerfilesCultivos perfilesCultivos);
	
	public PerfilesCultivos findOne (Long id );
	
	public void delete (Long id);
	
	public List<PerfilesCultivosQ> findAllById(Long id);
}
