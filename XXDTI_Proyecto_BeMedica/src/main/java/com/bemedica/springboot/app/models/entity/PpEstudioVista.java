package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="paque_perfil_estudio_vista")
public class PpEstudioVista implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pp_id")
	private Long PpId;
	
	@Column(name="estudio_nombre")
	private String EstudioNombre;
	
	@Column(name="paque_perfil_nombre")
	private String PaquePerfilNombre;
	
	
	
	@Column(name="paque_perfil_id")
	private String PaquePerfilId;



	public Long getPpId() {
		return PpId;
	}



	public void setPpId(Long ppId) {
		PpId = ppId;
	}



	public String getEstudioNombre() {
		return EstudioNombre;
	}



	public void setEstudioNombre(String estudioNombre) {
		EstudioNombre = estudioNombre;
	}



	public String getPaquePerfilNombre() {
		return PaquePerfilNombre;
	}



	public void setPaquePerfilNombre(String paquePerfilNombre) {
		PaquePerfilNombre = paquePerfilNombre;
	}



	public String getPaquePerfilId() {
		return PaquePerfilId;
	}



	public void setPaquePerfilId(String paquePerfilId) {
		PaquePerfilId = paquePerfilId;
	}
	
	
	
}
