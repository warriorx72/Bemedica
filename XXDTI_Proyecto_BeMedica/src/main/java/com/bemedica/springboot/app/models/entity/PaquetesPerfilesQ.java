package com.bemedica.springboot.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PaquetesPerfilesQ {

	@Id
	private Long pape_id;
	private Long paquete_id;
	private String perfil_nombre;
	
	public Long getPape_id() {
		return pape_id;
	}
	public void setPape_id(Long pape_id) {
		this.pape_id = pape_id;
	}
	public Long getPaquete_id() {
		return paquete_id;
	}
	public void setPaquete_id(Long paquete_id) {
		this.paquete_id = paquete_id;
	}
	public String getPerfil_nombre() {
		return perfil_nombre;
	}
	public void setPerfil_nombre(String perfil_nombre) {
		this.perfil_nombre = perfil_nombre;
	}
	
	
	
	
}
