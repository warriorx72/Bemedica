package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.VistaMedico;

public interface IVistaMedicoDao {

	public List<VistaMedico> findAll();

	

}
