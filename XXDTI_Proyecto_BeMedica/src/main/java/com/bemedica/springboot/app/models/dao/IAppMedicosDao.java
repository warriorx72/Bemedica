package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.AppMedicos;

public interface IAppMedicosDao{
	public List<AppMedicos> findAll();

	public AppMedicos findOne(Long MedicosId);

	public void save(AppMedicos medicos);

	public void delete(Long id);

	String exist(Long id);
}
