package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_solicitud_detalle")
public class SolicitudDetalles implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long solicitud_detalle_id;

	@Column
	private Long solicitud_id;
	@Column
	private Long estudio_id;
	@Column
	private Long cantidad;
	@Column
	private String tipo;
	public Long getSolicitud_detalle_id() {
		return solicitud_detalle_id;
	}
	public void setSolicitud_detalle_id(Long solicitud_detalle_id) {
		this.solicitud_detalle_id = solicitud_detalle_id;
	}
	public Long getSolicitud_id() {
		return solicitud_id;
	}
	public void setSolicitud_id(Long solicitud_id) {
		this.solicitud_id = solicitud_id;
	}
	public Long getEstudio_id() {
		return estudio_id;
	}
	public void setEstudio_id(Long estudio_id) {
		this.estudio_id = estudio_id;
	}
	public Long getCantidad() {
		return cantidad;
	}
	public void setCantidad(Long cantidad) {
		this.cantidad = cantidad;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
