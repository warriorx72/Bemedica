package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.VistaOrden;

public interface IVistaOrdenDao {
	public List<VistaOrden> findAll();

}
