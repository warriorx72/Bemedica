package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="perfiles")
public class Perfiles implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="perfil_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PerfilId;
	
	@Column(name="perfil_id_text")
	private String PerfilIdText;
	
	@Column(name="perfil_nombre")
	private String PerfilNombre;
	
	@Column(name="perfil_descripcion")
	private String PerfilDescripcion;
	
	@Column(name="perfil_envase")
	private String PerfilEnvase;
	
	@Column(name="perfil_area")
	private String PerfilArea;
	
	@Column(name="perfil_precio")
	private Float PerfilPrecio;
	
	@Column(name="perfil_indi_pa")
	private String PerfilIndiPa;
	
	@Column(name="perfil_indi_pe")
	private String PerfilIndiPe;
	
	@Column(name="perfil_individual")
	private boolean PerfilIndividual;
	
	@Column(name="perfil_estatus")
	private boolean PerfilEstatus;

	
	public boolean isPerfilEstatus() {
		return PerfilEstatus;
	}

	public void setPerfilEstatus(boolean perfilEstatus) {
		PerfilEstatus = perfilEstatus;
	}

	public Long getPerfilId() {
		return PerfilId;
	}

	public void setPerfilId(Long perfilId) {
		PerfilId = perfilId;
	}

	public String getPerfilIdText() {
		return PerfilIdText;
	}

	public void setPerfilIdText(String perfilIdText) {
		PerfilIdText = perfilIdText;
	}

	public String getPerfilNombre() {
		return PerfilNombre;
	}

	public void setPerfilNombre(String perfilNombre) {
		PerfilNombre = perfilNombre;
	}

	public String getPerfilDescripcion() {
		return PerfilDescripcion;
	}

	public void setPerfilDescripcion(String perfilDescripcion) {
		PerfilDescripcion = perfilDescripcion;
	}

	public String getPerfilEnvase() {
		return PerfilEnvase;
	}

	public void setPerfilEnvase(String perfilEnvase) {
		PerfilEnvase = perfilEnvase;
	}

	public String getPerfilArea() {
		return PerfilArea;
	}

	public void setPerfilArea(String perfilArea) {
		PerfilArea = perfilArea;
	}

	public Float getPerfilPrecio() {
		return PerfilPrecio;
	}

	public void setPerfilPrecio(Float perfilPrecio) {
		PerfilPrecio = perfilPrecio;
	}

	public String getPerfilIndiPa() {
		return PerfilIndiPa;
	}

	public void setPerfilIndiPa(String perfilIndiPa) {
		PerfilIndiPa = perfilIndiPa;
	}

	public String getPerfilIndiPe() {
		return PerfilIndiPe;
	}

	public void setPerfilIndiPe(String perfilIndiPe) {
		PerfilIndiPe = perfilIndiPe;
	}

	public boolean isPerfilIndividual() {
		return PerfilIndividual;
	}

	public void setPerfilIndividual(boolean perfilIndividual) {
		PerfilIndividual = perfilIndividual;
	}

	
	
	

}
