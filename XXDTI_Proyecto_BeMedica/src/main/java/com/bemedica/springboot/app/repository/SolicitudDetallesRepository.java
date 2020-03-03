package com.bemedica.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.bemedica.springboot.app.models.entity.SolicitudDetalles;

public interface SolicitudDetallesRepository extends CrudRepository<SolicitudDetalles, Long> {

}
