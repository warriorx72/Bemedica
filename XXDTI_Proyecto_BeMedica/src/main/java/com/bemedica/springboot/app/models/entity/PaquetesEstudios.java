package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="paquetes_estudios")
public class PaquetesEstudios implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="pe_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PeId;
	
	@Column(name="paquete_id")
	private Long PaqueteId;
	
	@Column(name="estudio_id")
	private Long EstudioId;

	public Long getPeId() {
		return PeId;
	}

	public void setPeId(Long peId) {
		PeId = peId;
	}

	public Long getPaqueteId() {
		return PaqueteId;
	}

	public void setPaqueteId(Long paqueteId) {
		PaqueteId = paqueteId;
	}

	public Long getEstudioId() {
		return EstudioId;
	}

	public void setEstudioId(Long estudioId) {
		EstudioId = estudioId;
	}
	
	

}
