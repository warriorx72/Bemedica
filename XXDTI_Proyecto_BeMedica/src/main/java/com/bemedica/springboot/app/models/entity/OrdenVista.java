package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="orden_vista")
public class OrdenVista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="orden_id")
	private String OrdenId;
	
	@Column(name="orden_folio")
	private String OrdenFolio;
	
	@Column(name="orden_estatus")
	private String OrdenEstatus;
	
	@Column(name="orden_fecha")
	@Temporal(TemporalType.DATE)
	private Date OrdenFecha;
	
	@Column(name="orden_cotizacion")
	private String OrdenCotizacion;
	
	@Column(name="orden_urgencia")
	private String OrdenUrgencia;
	
	@Column(name="paciente_nombre")
	private String PacienteNombre;
	
	@Column(name="orden_confidencial")
	private String OrdenConfidencial;
	
	@Column(name="paciente_ap")
	private String PacienteAP;
	
	@Column(name="paciente_am")
	private String PacienteAM;
	
	@Column(name="empresa_nombre")
	private String EmpresaNombre;
	
	@Column(name="medico_nombre")
	private String MedicoNombre;
	
	@Column(name="medico_ap")
	private String MedicoAP;
	
	@Column(name="medico_am")
	private String MedicoAM;
	
	@Column(name="factura_id_text")
	private String FacturaIdText;
	
	@Column(name="estudio_nombre")
	private String EstudioNombre;
	
	
	
	
	public String getEstudioNombre() {
		return EstudioNombre;
	}
	public void setEstudioNombre(String estudioNombre) {
		EstudioNombre = estudioNombre;
	}
	public String getOrdenId() {
		return OrdenId;
	}
	public void setOrdenId(String ordenId) {
		OrdenId = ordenId;
	}
	public String getFacturaIdText() {
		return FacturaIdText;
	}
	public void setFacturaIdText(String facturaIdText) {
		FacturaIdText = facturaIdText;
	}
	public String getOrdenFolio() {
		return OrdenFolio;
	}
	public void setOrdenFolio(String ordenFolio) {
		OrdenFolio = ordenFolio;
	}
	public String getOrdenEstatus() {
		return OrdenEstatus;
	}
	public void setOrdenEstatus(String ordenEstatus) {
		OrdenEstatus = ordenEstatus;
	}
	public Date getOrdenFecha() {
		return OrdenFecha;
	}
	public void setOrdenFecha(Date ordenFecha) {
		OrdenFecha = ordenFecha;
	}
	public String getOrdenCotizacion() {
		return OrdenCotizacion;
	}
	public void setOrdenCotizacion(String ordenCotizacion) {
		OrdenCotizacion = ordenCotizacion;
	}
	public String getOrdenUrgencia() {
		return OrdenUrgencia;
	}
	public void setOrdenUrgencia(String ordenUrgencia) {
		OrdenUrgencia = ordenUrgencia;
	}
	public String getPacienteNombre() {
		return PacienteNombre;
	}
	public void setPacienteNombre(String pacienteNombre) {
		PacienteNombre = pacienteNombre;
	}
	public String getOrdenConfidencial() {
		return OrdenConfidencial;
	}
	public void setOrdenConfidencial(String ordenConfidencial) {
		OrdenConfidencial = ordenConfidencial;
	}
	public String getPacienteAP() {
		return PacienteAP;
	}
	public void setPacienteAP(String pacienteAP) {
		PacienteAP = pacienteAP;
	}
	public String getPacienteAM() {
		return PacienteAM;
	}
	public void setPacienteAM(String pacienteAM) {
		PacienteAM = pacienteAM;
	}
	public String getEmpresaNombre() {
		return EmpresaNombre;
	}
	public void setEmpresaNombre(String empresaNombre) {
		EmpresaNombre = empresaNombre;
	}
	public String getMedicoNombre() {
		return MedicoNombre;
	}
	public void setMedicoNombre(String medicoNombre) {
		MedicoNombre = medicoNombre;
	}
	public String getMedicoAP() {
		return MedicoAP;
	}
	public void setMedicoAP(String medicoAP) {
		MedicoAP = medicoAP;
	}
	public String getMedicoAM() {
		return MedicoAM;
	}
	public void setMedicoAM(String medicoAM) {
		MedicoAM = medicoAM;
	}
	
}
