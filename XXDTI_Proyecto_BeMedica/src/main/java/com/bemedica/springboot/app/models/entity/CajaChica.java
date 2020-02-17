package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="caja_chica")
public class CajaChica implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="caja_chica_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CajaChicaId;
	
	@Column(name="caja_chica_monto")	
	private String CajaChicaMonto;
	
	@Column(name="caja_chica_fecha")	
	private String CajaChicaFecha;
	
	@Column(name="sucursal_id")	
	private String IdSucursal;

	public Long getCajaChicaId() {
		return CajaChicaId;
	}

	public void setCajaChicaId(Long cajaChicaId) {
		CajaChicaId = cajaChicaId;
	}

	public String getCajaChicaMonto() {
		return CajaChicaMonto;
	}

	public void setCajaChicaMonto(String cajaChicaMonto) {
		CajaChicaMonto = cajaChicaMonto;
	}
	

	public String getCajaChicaFecha() {
		return CajaChicaFecha;
	}

	public void setCajaChicaFecha(String cajaChicaFecha) {
		CajaChicaFecha = cajaChicaFecha;
	}
	
	
	public String getIdSucursal() {
		return IdSucursal;
	}

	public void setIdSucursal(String idSucursal) {
		IdSucursal =idSucursal;
	}


}
