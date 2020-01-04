package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.ConvenioPerfil;

public interface IConvenioPerfilDao {
	
	public List<ConvenioPerfil> findAll();
	
	public void save (ConvenioPerfil cpe);
	
	public ConvenioPerfil findOne (Long id );
	
	public void delete (Long id);
	
	public List<Object[]> findPerfil(Long id);
	
	public List<Object[]> findPerfilConvenio(Long id);

}
