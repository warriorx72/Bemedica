package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Sucursal;

public interface ISucursalDao {

	public List<Sucursal> findAll();

	

}
