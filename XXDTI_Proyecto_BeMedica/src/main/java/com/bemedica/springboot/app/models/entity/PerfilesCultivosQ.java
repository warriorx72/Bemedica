package com.bemedica.springboot.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PerfilesCultivosQ {
	
	@Id
	private Long pecu_id;
	private Long perfil_id;
	private String estudio_nombre;
	public Long getPecu_id() {
		return pecu_id;
	}
	public void setPecu_id(Long pecu_id) {
		this.pecu_id = pecu_id;
	}
	public Long getPerfil_id() {
		return perfil_id;
	}
	public void setPerfil_id(Long perfil_id) {
		this.perfil_id = perfil_id;
	}
	public String getEstudio_nombre() {
		return estudio_nombre;
	}
	public void setEstudio_nombre(String estudio_nombre) {
		this.estudio_nombre = estudio_nombre;
	}
	
	
}
