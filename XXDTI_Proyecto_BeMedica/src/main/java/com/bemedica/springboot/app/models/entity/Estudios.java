package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="estudios")
public class Estudios implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name="estudio_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long EstudioId;
	
	@Column(name="estudio_id_text")
	private String EstudioIdText;
	
	@Column(name="estudio_nombre")
	private String EstudioNombre;

	@Column(name="estudio_descripcion")
	private String EstudioDescripcion;

	@Column(name="estudio_precio")
	private String EstudioPrecio;
	
	@Column(name="bemedica_id")
	private String BeMedicaId;
	
	@Column(name="estudio_individual")
	private Boolean EstudioIndividual;
	
	@Column(name="estudio_estatus")
	private Boolean EstudioEstatus;
	
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
	
	@Column(name="estudio_entrega")
	private String EstudioEntrega;
	
	@Column(name="estudio_indicaciones_pa")
	private String EstudioIndicacionesPa;
	
	@Column(name="estudio_indicaciones_pe")
	private String EstudioIndicacionesPe;
	
	@Column(name="estudio_decimales")
	private String EstudioDecimales;
	
	@Column(name="estudio_tipo")
	private String EstudioTipo;
	
	@Column(name="estudio_vr_i")
	private String EstudioVrI;
	
	public String getEstudioVrI() {
		return EstudioVrI;
	}

	public void setEstudioVrI(String estudioVrI) {
		EstudioVrI = estudioVrI;
	}

	public Boolean getEstudioIndividual() {
		return EstudioIndividual;
	}

	public void setEstudioIndividual(Boolean estudioIndividual) {
		EstudioIndividual = estudioIndividual;
	}

	public Boolean getEstudioEstatus() {
		return EstudioEstatus;
	}

	public void setEstudioEstatus(Boolean estudioEstatus) {
		EstudioEstatus = estudioEstatus;
	}

	public String getEstudioTipo() {
		return EstudioTipo;
	}

	public void setEstudioTipo(String estudioTipo) {
		EstudioTipo = estudioTipo;
	}

	public String getEstudioDecimales() {
		return EstudioDecimales;
	}

	public void setEstudioDecimales(String estudioDecimales) {
		EstudioDecimales = estudioDecimales;
	}

	public String getEstudioEntrega() {
		return EstudioEntrega;
	}

	public void setEstudioEntrega(String estudioEntrega) {
		EstudioEntrega = estudioEntrega;
	}

	public String getEstudioIndicacionesPa() {
		return EstudioIndicacionesPa;
	}

	public void setEstudioIndicacionesPa(String estudioIndicacionesPa) {
		EstudioIndicacionesPa = estudioIndicacionesPa;
	}

	public String getEstudioIndicacionesPe() {
		return EstudioIndicacionesPe;
	}

	public void setEstudioIndicacionesPe(String estudioIndicacionesPe) {
		EstudioIndicacionesPe = estudioIndicacionesPe;
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

	public String getEstudioPrecio() {
		return EstudioPrecio;
	}

	public void setEstudioPrecio(String estudioPrecio) {
		EstudioPrecio = estudioPrecio;
	}

	public String getBeMedicaId() {
		return BeMedicaId;
	}

	public void setBeMedicaId(String beMedicaId) {
		BeMedicaId = beMedicaId;
	}

	
	
}
