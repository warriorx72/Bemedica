package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="paquetes_perfiles")
public class PaquetesPerfiles implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pape_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PaPeId;
	
	@Column(name="paquete_id")
	private Long PaqueteId;
	
	@Column(name="perfil_id")
	private Long PerfilId;

	public Long getPaPeId() {
		return PaPeId;
	}

	public void setPaPeId(Long paPeId) {
		PaPeId = paPeId;
	}

	public Long getPaqueteId() {
		return PaqueteId;
	}

	public void setPaqueteId(Long paqueteId) {
		PaqueteId = paqueteId;
	}

	public Long getPerfilId() {
		return PerfilId;
	}

	public void setPerfilId(Long perfilId) {
		PerfilId = perfilId;
	}
	
	
}
