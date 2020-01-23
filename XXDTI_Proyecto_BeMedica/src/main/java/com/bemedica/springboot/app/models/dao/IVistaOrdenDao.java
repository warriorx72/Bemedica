package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.VistaOrden;
import com.bemedica.springboot.app.models.entity.VistaOrdenEstudio;

public interface IVistaOrdenDao {
	public List<VistaOrden> findAll(Long orden_id);
	public List<VistaOrden> findAll2(String id_sucursal);
	public List<VistaOrden> vo(Long orden_id);
}
