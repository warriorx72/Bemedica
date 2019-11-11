package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Catalogo;

public interface ICatalogoDao {
	
	public List<Catalogo> findAll();
	
	public Catalogo findOne(Long CatalogoId); 
	
	public void delete(Long CatalogoId);

	public void save( Catalogo catalogo);

}
