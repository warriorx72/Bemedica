package com.bemedica.springboot.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class EstudiosListQ {
	@Id
	private Long estudio_id;
	private String estudio_nombre;
	private String estudio_tipo;
	
	public String getEstudio_tipo() {
		return estudio_tipo;
	}
	public void setEstudio_tipo(String estudio_tipo) {
		this.estudio_tipo = estudio_tipo;
	}
	public Long getEstudio_id() {
		return estudio_id;
	}
	public void setEstudio_id(Long estudio_id) {
		this.estudio_id = estudio_id;
	}
	public String getEstudio_nombre() {
		return estudio_nombre;
	}
	public void setEstudio_nombre(String estudio_nombre) {
		this.estudio_nombre = estudio_nombre;
	}
	
	
}
