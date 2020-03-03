package com.bemedica.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;

import com.bemedica.springboot.app.models.entity.AppPaciente;

public interface AppPacienteRepository extends CrudRepository<AppPaciente, Long> {

}
