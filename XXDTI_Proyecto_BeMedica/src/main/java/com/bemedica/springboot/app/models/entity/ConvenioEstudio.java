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
@Table(name="convenio_estudio")
public class ConvenioEstudio implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ce_id")
	private Long CeId;
	
	
	@NotNull
	@Column(name = "convenio_id")
	private Long ConvenioId;
	
	
	@NotNull
	@Column(name = "estudio_id")
	private Long EstudioId;
	
	
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
	
	

}
