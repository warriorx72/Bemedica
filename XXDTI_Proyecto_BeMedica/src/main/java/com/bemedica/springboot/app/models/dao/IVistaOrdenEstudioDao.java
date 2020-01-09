package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.VistaOrdenEstudio;

public interface IVistaOrdenEstudioDao {
	public List<VistaOrdenEstudio> findAll();

	public List<VistaOrdenEstudio> voe(Long orden_id);
	
	List<Object> emp_suc(String rol, String user);
}
