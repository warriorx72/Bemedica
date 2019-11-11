package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.OrdenVista;

public interface IOrdenVistaDao {
	public List<OrdenVista> findAll();
}
