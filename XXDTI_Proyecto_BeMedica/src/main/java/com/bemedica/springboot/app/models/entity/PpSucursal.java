package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="pp_sucursal")
public class PpSucursal implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pps_id")
	private Long PpsId;
	
	@Column(name = "paque_perfil_id")
	private Long PaquePerfilId;
	
	
	@Column(name = "sucursal_id")
	private Long SucursalId;


	public Long getPpsId() {
		return PpsId;
	}


	public void setPpsId(Long ppsId) {
		PpsId = ppsId;
	}


	public Long getPaquePerfilId() {
		return PaquePerfilId;
	}


	public void setPaquePerfilId(Long paquePerfilId) {
		PaquePerfilId = paquePerfilId;
	}


	public Long getSucursalId() {
		return SucursalId;
	}


	public void setSucursalId(Long sucursalId) {
		SucursalId = sucursalId;
	}
	
	

}
