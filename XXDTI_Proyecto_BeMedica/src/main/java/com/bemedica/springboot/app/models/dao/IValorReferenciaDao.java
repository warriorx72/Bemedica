package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.ValorReferencia;

public interface IValorReferenciaDao {
	
	public List<ValorReferencia> findAll();
	
	public void save (ValorReferencia valorReferencia);
	
	public ValorReferencia findOne (Long id );
	
	public void delete (Long id);
	
	public List<ValorReferencia> findAllById(Long id );


}
