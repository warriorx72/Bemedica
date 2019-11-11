package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.CajaVista;

public interface ICajaVistaDao {

	public List<CajaVista> findAll();
}