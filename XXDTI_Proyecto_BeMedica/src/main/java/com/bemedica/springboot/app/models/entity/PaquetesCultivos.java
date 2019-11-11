package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="paquetes_cultivos")
public class PaquetesCultivos implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="pacu_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PaCuId;
	
	@Column(name="paquete_id")
	private Long PaqueteId;
	
	@Column(name="cultivo_id")
	private Long CultivoId;

	public Long getPaCuId() {
		return PaCuId;
	}

	public void setPaCuId(Long paCuId) {
		PaCuId = paCuId;
	}

	public Long getPaqueteId() {
		return PaqueteId;
	}

	public void setPaqueteId(Long paqueteId) {
		PaqueteId = paqueteId;
	}

	public Long getCultivoId() {
		return CultivoId;
	}

	public void setCultivoId(Long cultivoId) {
		CultivoId = cultivoId;
	}
	
	

}
