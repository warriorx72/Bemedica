package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="perfiles_cultivos")
public class PerfilesCultivos implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="pecu_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PeCuId;
	
	@Column(name="perfil_id")
	private Long PerfilId;
	
	@Column(name="cultivo_id")
	private Long CultivoId;

	public Long getPeCuId() {
		return PeCuId;
	}

	public void setPeCuId(Long peCuId) {
		PeCuId = peCuId;
	}

	public Long getPerfilId() {
		return PerfilId;
	}

	public void setPerfilId(Long perfilId) {
		PerfilId = perfilId;
	}

	public Long getCultivoId() {
		return CultivoId;
	}

	public void setCultivoId(Long cultivoId) {
		CultivoId = cultivoId;
	}
	
	
}
