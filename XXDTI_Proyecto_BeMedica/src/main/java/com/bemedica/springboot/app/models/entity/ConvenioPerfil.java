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
@Table(name="convenio_perfil")
public class ConvenioPerfil implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cpe_id")
	private Long CpeId;
	
	
	@NotNull
	@Column(name = "convenio_id")
	private Long ConvenioId;
	
	
	@NotNull
	@Column(name = "perfil_id")
	private Long PerfilId;


	public Long getCpeId() {
		return CpeId;
	}


	public void setCpeId(Long cpeId) {
		CpeId = cpeId;
	}


	public Long getConvenioId() {
		return ConvenioId;
	}


	public void setConvenioId(Long convenioId) {
		ConvenioId = convenioId;
	}


	public Long getPerfilId() {
		return PerfilId;
	}


	public void setPerfilId(Long perfilId) {
		PerfilId = perfilId;
	}
	
	
}
