package com.bemedica.springboot.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
@Entity
public class PerfilesListQ {
	
	@Id
	private Long perfil_id;
	private String perfil_nombre;
	public Long getPerfil_id() {
		return perfil_id;
	}
	public void setPerfil_id(Long perfil_id) {
		this.perfil_id = perfil_id;
	}
	public String getPerfil_nombre() {
		return perfil_nombre;
	}
	public void setPerfil_nombre(String perfil_nombre) {
		this.perfil_nombre = perfil_nombre;
	}
	
	

}
