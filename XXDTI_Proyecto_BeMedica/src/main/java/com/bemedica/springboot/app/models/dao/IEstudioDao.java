package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.Estudio;

public interface IEstudioDao {

	public List<Estudio> findAll();
	public List<Estudio> findBy();
	public List<Estudio> findAntibiograma();
	public List<Estudio> findCultivo();
	public List<Estudio> findGabinete();
	public List<Object[]> findConvenio();
	public void save(Estudio estudio);
	
	public Estudio findOne(Long id);
	
	public void delete(Long id);

}
