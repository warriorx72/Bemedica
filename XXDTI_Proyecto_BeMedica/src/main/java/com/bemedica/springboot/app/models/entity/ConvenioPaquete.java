package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="convenio_paquete")
public class ConvenioPaquete implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "copa_id")
	private Long CopaId;
	
	
	@NotNull
	@Column(name = "convenio_id")
	private Long ConvenioId;
	
	
	@NotNull
	@Column(name = "paquete_id")
	private Long PaqueteId;


	public Long getCopaId() {
		return CopaId;
	}


	public void setCopaId(Long copaId) {
		CopaId = copaId;
	}


	public Long getConvenioId() {
		return ConvenioId;
	}


	public void setConvenioId(Long convenioId) {
		ConvenioId = convenioId;
	}


	public Long getPaqueteId() {
		return PaqueteId;
	}


	public void setPaqueteId(Long paqueteId) {
		PaqueteId = paqueteId;
	}
	


}
