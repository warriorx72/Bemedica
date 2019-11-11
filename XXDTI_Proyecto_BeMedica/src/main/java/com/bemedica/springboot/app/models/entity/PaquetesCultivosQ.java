package com.bemedica.springboot.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PaquetesCultivosQ {
	@Id
	private Long pacu_id;
	private Long paquete_id;
	private String estudio_nombre;
	public Long getPacu_id() {
		return pacu_id;
	}
	public void setPacu_id(Long pacu_id) {
		this.pacu_id = pacu_id;
	}
	public Long getPaquete_id() {
		return paquete_id;
	}
	public void setPaquete_id(Long paquete_id) {
		this.paquete_id = paquete_id;
	}
	public String getEstudio_nombre() {
		return estudio_nombre;
	}
	public void setEstudio_nombre(String estudio_nombre) {
		this.estudio_nombre = estudio_nombre;
	}

	
	
}
