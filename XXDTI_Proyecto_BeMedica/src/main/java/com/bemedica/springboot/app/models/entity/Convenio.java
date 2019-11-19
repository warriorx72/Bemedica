package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
@Table(name="convenio")
public class Convenio implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="convenio_id")
	private Long ConvenioId;
	
	
	@Column(name="convenio_id_text")
	private String ConvenioIdText;
	
	@NotEmpty
	@Column(name="convenio_nombre")
	private String ConvenioNombre;
	
	@NotEmpty
	@Column(name="convenio_detalles")
	private String ConvenioDetalles;
	
	@NotNull
	@Column(name="convenio_costo")
	private Double ConvenioCosto;
	
	@NotNull
	@Column(name="empresa_id")
	private Long EmpresaId;
	
	
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
	public Long getEmpresaId() {
		return EmpresaId;
	}
	public void setEmpresaId(Long empresaId) {
		EmpresaId = empresaId;
	}
	public String getConvenioIdText() {
		return ConvenioIdText;
	}
	public void setConvenioIdText(String convenioIdText) {
		ConvenioIdText = convenioIdText;
	}
	
}
