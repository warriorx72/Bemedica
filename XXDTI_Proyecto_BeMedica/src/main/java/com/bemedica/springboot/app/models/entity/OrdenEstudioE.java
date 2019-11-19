package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="orden_estudio")
public class OrdenEstudioE implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orden_estudio_id")
	private Long OrdenEstudioId;
	
	@Column(name="orden_id")
	private Long OrdenId;
	
	@Column(name="estudio_id")
	private Long EstudioId;
	
	@Column(name="cantidad_estudio")
	private Long CantidadEstudio;
	
	@Column(name="total_linea")
	private Double TotalLinea;
	
	@Column(name="tipo")
	private String Tipo;
	
	@Column(name="captura")
	private String Captura;
	
	@Column(name="comentario")
	private String Comentario;

	public Long getOrdenEstudioId() {
		return OrdenEstudioId;
	}

	public void setOrdenEstudioId(Long ordenEstudioId) {
		OrdenEstudioId = ordenEstudioId;
	}

	public Long getOrdenId() {
		return OrdenId;
	}

	public void setOrdenId(Long ordenId) {
		OrdenId = ordenId;
	}

	public Long getEstudioId() {
		return EstudioId;
	}

	public void setEstudioId(Long estudioId) {
		EstudioId = estudioId;
	}

	public Long getCantidadEstudio() {
		return CantidadEstudio;
	}

	public void setCantidadEstudio(Long cantidadEstudio) {
		CantidadEstudio = cantidadEstudio;
	}

	public Double getTotalLinea() {
		return TotalLinea;
	}

	public void setTotalLinea(Double totalLinea) {
		TotalLinea = totalLinea;
	}

	public String getTipo() {
		return Tipo;
	}

	public void setTipo(String tipo) {
		Tipo = tipo;
	}

	public String getCaptura() {
		return Captura;
	}

	public void setCaptura(String captura) {
		Captura = captura;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getComentario() {
		return Comentario;
	}

	public void setComentario(String comentario) {
		Comentario = comentario;
	}
	
	
	

}
