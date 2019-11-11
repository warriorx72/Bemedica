package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.AntiCatalogos;
import com.bemedica.springboot.app.models.entity.AntiCatalogosQ;
public interface IAntiCatalogosDao {
	public List<AntiCatalogos> findAll();
	
	public void save (AntiCatalogos antiCatalogos);
	
	public AntiCatalogos findOne (Long id );
	
	public void delete (Long id);
	
	public List<AntiCatalogosQ> findAllById(Long id);
	
	public List<AntiCatalogos> findCatalogos(Long id);
}
