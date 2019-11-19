package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="paque_perfil_sucursal_vista")
public class PpSucursalVista implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pps_id")
	private Long PpsId;    
	
	
	@Column(name="paque_perfil_id")
	private Long paque_perfil_id;
	
	@Column(name="sucursal_id")
	private Long SucursalId;
	
	@Column(name="sucursal_nombre")
	private String SucursalNombre;
	
	@Column(name="paque_perfil_nombre")
	private String PaquePerfilNombre;

	public Long getPpsId() {
		return PpsId;
	}

	public void setPpsId(Long ppsId) {
		PpsId = ppsId;
	}

	public Long getPaque_perfil_id() {
		return paque_perfil_id;
	}

	public void setPaque_perfil_id(Long paque_perfil_id) {
		this.paque_perfil_id = paque_perfil_id;
	}

	public Long getSucursalId() {
		return SucursalId;
	}

	public void setSucursalId(Long sucursalId) {
		SucursalId = sucursalId;
	}

	public String getSucursalNombre() {
		return SucursalNombre;
	}

	public void setSucursalNombre(String sucursalNombre) {
		SucursalNombre = sucursalNombre;
	}

	public String getPaquePerfilNombre() {
		return PaquePerfilNombre;
	}

	public void setPaquePerfilNombre(String paquePerfilNombre) {
		PaquePerfilNombre = paquePerfilNombre;
	}

	


	
}
