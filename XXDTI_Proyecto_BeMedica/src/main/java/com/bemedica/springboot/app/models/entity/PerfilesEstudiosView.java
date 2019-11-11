package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="perfiles_estudios_view")
public class PerfilesEstudiosView implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="perfiles_estudios_id")
	private Long PerfilesEstudiosId;
	
	@Column(name="estudio_id")
	private Long EstudioId;
	
	@Column(name="perfil_id")
	private Long PerfilId;
	
	@Column(name="estudio_nombre")
	private String EstudioNombre;

	
	public Long getPerfilesEstudiosId() {
		return PerfilesEstudiosId;
	}

	public void setPerfilesEstudiosId(Long perfilesEstudiosId) {
		PerfilesEstudiosId = perfilesEstudiosId;
	}

	public Long getEstudioId() {
		return EstudioId;
	}

	public void setEstudioId(Long estudioId) {
		EstudioId = estudioId;
	}

	public Long getPerfilId() {
		return PerfilId;
	}

	public void setPerfilId(Long perfilId) {
		PerfilId = perfilId;
	}

	public String getEstudioNombre() {
		return EstudioNombre;
	}

	public void setEstudioNombre(String estudioNombre) {
		EstudioNombre = estudioNombre;
	}
	
	

}
