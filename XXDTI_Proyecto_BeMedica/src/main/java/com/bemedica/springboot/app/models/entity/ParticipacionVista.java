package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "participacion_vista")
public class ParticipacionVista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Column(name="Id")
	private int IdMedico;
	
	@Column(name="Id_text")
	private String IdText;
	
	@Column(name="Nombre_Medico")	
	private String NombreMedico;
	
	@Id
	@Column(name="Orden")
	private int Orden;
	
	@Column(name="Monto_Orden")
	private Long MontoOrden;
	
	/**********************************************************************************************/

	public int getIdMedico() {
		return IdMedico;
	}

	public void setIdMedico(int idMedico) {
		IdMedico = idMedico;
	}

	public String getIdText() {
		return IdText;
	}

	public void setIdText(String idText) {
		IdText = idText;
	}

	public String getNombreMedico() {
		return NombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		NombreMedico = nombreMedico;
	}

	public int getOrden() {
		return Orden;
	}

	public void setOrden(int orden) {
		Orden = orden;
	}

	public Long getMontoOrden() {
		return MontoOrden;
	}

	public void setMontoOrden(Long montoOrden) {
		MontoOrden = montoOrden;
	}
	
}
