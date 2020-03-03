package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="app_solicitud")
public class SolicitudOrden implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long solicitud_id;
	
	@Column
	private Long medico_id;
	@Column
	private Long paciente_id;
	@Column
	private String estatus;
	@Column
	private String monto;
	@Column
	private String fecha_solicitud;
	public Long getSolicitud_id() {
		return solicitud_id;
	}
	public void setSolicitud_id(Long solicitud_id) {
		this.solicitud_id = solicitud_id;
	}
	public Long getMedico_id() {
		return medico_id;
	}
	public void setMedico_id(Long medico_id) {
		this.medico_id = medico_id;
	}
	public Long getPaciente_id() {
		return paciente_id;
	}
	public void setPaciente_id(Long paciente_id) {
		this.paciente_id = paciente_id;
	}
	public String getEstatus() {
		return estatus;
	}
	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getFecha_solicitud() {
		return fecha_solicitud;
	}
	public void setFecha_solicitud(String fecha_solicitud) {
		this.fecha_solicitud = fecha_solicitud;
	}
	
}