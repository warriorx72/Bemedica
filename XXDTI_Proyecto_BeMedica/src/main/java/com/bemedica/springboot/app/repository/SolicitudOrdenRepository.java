package com.bemedica.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.SolicitudOrden;

@Repository
public interface SolicitudOrdenRepository extends CrudRepository<SolicitudOrden,Long>{
	
}