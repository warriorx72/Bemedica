package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="convenioestudio_vista")
public class ConvenioEstudioVista implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ce_id")
	private Long CeId;
	
	@Column(name="convenio_id")
	private Long ConvenioId;
	
	@Column(name="estudio_id")
	private Long EstudioId;
	
	@Column(name="convenio_nombre")
	private String ConvenioNombre;
	
	@Column(name="estudio_nombre")
	private String EstudioNombre;
	
	
	public Long getCeId() {
		return CeId;
	}
	public void setCeId(Long ceId) {
		CeId = ceId;
	}
	public Long getConvenioId() {
		return ConvenioId;
	}
	public void setConvenioId(Long convenioId) {
		ConvenioId = convenioId;
	}
	public Long getEstudioId() {
		return EstudioId;
	}
	public void setEstudioId(Long estudioId) {
		EstudioId = estudioId;
	}
	public String getConvenioNombre() {
		return ConvenioNombre;
	}
	public void setConvenioNombre(String convenioNombre) {
		ConvenioNombre = convenioNombre;
	}
	public String getEstudioNombre() {
		return EstudioNombre;
	}
	public void setEstudioNombre(String estudioNombre) {
		EstudioNombre = estudioNombre;
	}
	
	
	

}
