package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="convenio_vista")
public class ConvenioVista implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="convenio_id")
	private Long ConvenioId;
	
	
	@Column(name="convenio_id_text")
	private String ConvenioIdText;

	@Column(name="convenio_nombre")
	private String ConvenioNombre;
	
	
	@Column(name="convenio_detalles")
	private String ConvenioDetalles;
	

	@Column(name="convenio_costo")
	private Double ConvenioCosto;
	

	@Column(name="empresa_nombre")
	private String EmpresaNombre;
	
	
	public Long getConvenioId() {
		return ConvenioId;
	}
	public void setConvenioId(Long convenioId) {
		ConvenioId = convenioId;
	}
	public String getConvenioNombre() {
		return ConvenioNombre;
	}
	public void setConvenioNombre(String convenioNombre) {
		ConvenioNombre = convenioNombre;
	}
	public String getConvenioDetalles() {
		return ConvenioDetalles;
	}
	public void setConvenioDetalles(String convenioDetalles) {
		ConvenioDetalles = convenioDetalles;
	}
	public Double getConvenioCosto() {
		return ConvenioCosto;
	}
	public void setConvenioCosto(Double convenioCosto) {
		ConvenioCosto = convenioCosto;
	}
	public String getEmpresaNombre() {
		return EmpresaNombre;
	}
	public void setEmpresaNombre(String empresaNombre) {
		EmpresaNombre = empresaNombre;
	}
	public String getConvenioIdText() {
		return ConvenioIdText;
	}
	public void setConvenioIdText(String convenioIdText) {
		ConvenioIdText = convenioIdText;
	}
	
	
	
}