package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="perfiles_estudios")
public class PerfilesEstudios implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="perfiles_estudios_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PerfilesEstudiosId;
	
	@Column(name="perfil_id")
	private Long PerfilId;
	
	@Column(name="estudio_id")
	private Long EstudioId;

	public Long getPerfilesEstudiosId() {
		return PerfilesEstudiosId;
	}

	public void setPerfilesEstudiosId(Long perfilesEstudiosId) {
		PerfilesEstudiosId = perfilesEstudiosId;
	}

	public Long getPerfilId() {
		return PerfilId;
	}

	public void setPerfilId(Long perfilId) {
		PerfilId = perfilId;
	}

	public Long getEstudioId() {
		return EstudioId;
	}

	public void setEstudioId(Long estudioId) {
		EstudioId = estudioId;
	}

}
