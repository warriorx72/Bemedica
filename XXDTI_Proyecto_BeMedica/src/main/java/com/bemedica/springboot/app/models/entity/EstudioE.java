package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="estudios")
public class EstudioE implements Serializable  {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="estudio_id")
	private Long EstudioId;
	
	@Column(name="estudio_id_text")
	private String EstudioIdText;
	
	@Column(name="estudio_nombre")
	private String EstudioNombre;
	
	@Column(name="estudio_descripcion")
	private String EstudioDescripcion;
	
	@Column(name="estudio_precio")
	private Double EstudioPrecio;

	
	@Column(name="bemedica_id")
	private String BeMedicaId;
	
	@Column(name="estudio_individual")
	private String EstudioIndividual;
	
	@Column(name="estudio_nombre_rep")
	private String EstudioNombreRep;
	
	@Column(name="estudio_area")
	private String EstudioArea;
	
	@Column(name="estudio_envases")
	private String EstudioEnvases;
	
	@Column(name="estudio_muestra")
	private String EstudioMuestra;
	
	@Column(name="estudio_unidades_res")
	private String EstudioUnidadesRes;
	
	@Column(name="estudio_alertas")
	private String EstudioAlertas;
	
	@Column(name="estudio_medida")
	private String EstudioMedida;
	
	@Column(name="estudio_tecnica")
	private String EstudioTecnica;
	
	
	public String getEstudioIndividual() {
		return EstudioIndividual;
	}

	public void setEstudioIndividual(String estudioIndividual) {
		EstudioIndividual = estudioIndividual;
	}

	public String getEstudioNombreRep() {
		return EstudioNombreRep;
	}

	public void setEstudioNombreRep(String estudioNombreRep) {
		EstudioNombreRep = estudioNombreRep;
	}

	public String getEstudioArea() {
		return EstudioArea;
	}

	public void setEstudioArea(String estudioArea) {
		EstudioArea = estudioArea;
	}

	public String getEstudioEnvases() {
		return EstudioEnvases;
	}

	public void setEstudioEnvases(String estudioEnvases) {
		EstudioEnvases = estudioEnvases;
	}

	public String getEstudioMuestra() {
		return EstudioMuestra;
	}

	public void setEstudioMuestra(String estudioMuestra) {
		EstudioMuestra = estudioMuestra;
	}

	public String getEstudioUnidadesRes() {
		return EstudioUnidadesRes;
	}

	public void setEstudioUnidadesRes(String estudioUnidadesRes) {
		EstudioUnidadesRes = estudioUnidadesRes;
	}

	public String getEstudioAlertas() {
		return EstudioAlertas;
	}

	public void setEstudioAlertas(String estudioAlertas) {
		EstudioAlertas = estudioAlertas;
	}

	public String getEstudioMedida() {
		return EstudioMedida;
	}

	public void setEstudioMedida(String estudioMedida) {
		EstudioMedida = estudioMedida;
	}

	public String getEstudioTecnica() {
		return EstudioTecnica;
	}

	public void setEstudioTecnica(String estudioTecnica) {
		EstudioTecnica = estudioTecnica;
	}

	public Long getEstudioId() {
		return EstudioId;
	}

	public void setEstudioId(Long estudioId) {
		EstudioId = estudioId;
	}

	public String getEstudioIdText() {
		return EstudioIdText;
	}

	public void setEstudioIdText(String estudioIdText) {
		EstudioIdText = estudioIdText;
	}

	public String getEstudioNombre() {
		return EstudioNombre;
	}

	public void setEstudioNombre(String estudioNombre) {
		EstudioNombre = estudioNombre;
	}

	public String getEstudioDescripcion() {
		return EstudioDescripcion;
	}

	public void setEstudioDescripcion(String estudioDescripcion) {
		EstudioDescripcion = estudioDescripcion;
	}

	public Double getEstudioPrecio() {
		return EstudioPrecio;
	}

	public void setEstudioPrecio(Double estudioPrecio) {
		EstudioPrecio = estudioPrecio;
	}



	public String getBeMedicaId() {
		return BeMedicaId;
	}

	public void setBeMedicaId(String beMedicaId) {
		BeMedicaId = beMedicaId;
	}
	
	
	

}
