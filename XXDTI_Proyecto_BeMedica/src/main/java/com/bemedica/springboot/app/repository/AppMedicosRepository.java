package com.bemedica.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.bemedica.springboot.app.models.entity.AppMedicos;

public interface AppMedicosRepository extends CrudRepository<AppMedicos, Long> {

}
