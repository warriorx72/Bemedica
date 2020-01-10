package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="paquetes_gabinete")
public class PaquetesGabinetes implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="paga_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PaGaId;
	
	@Column(name="paquete_id")
	private Long PaqueteId;
	
	@Column(name="gabinete_id")
	private Long GabineteId;

	public Long getPaGaId() {
		return PaGaId;
	}

	public void setPaGaId(Long paPeId) {
		PaGaId = paPeId;
	}

	public Long getPaqueteId() {
		return PaqueteId;
	}

	public void setPaqueteId(Long paqueteId) {
		PaqueteId = paqueteId;
	}

	public Long getGabineteId() {
		return GabineteId;
	}

	public void setGabineteId(Long gabineteId) {
		GabineteId = gabineteId;
	}

	
}
