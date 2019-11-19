package com.bemedica.springboot.app.models.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="empresa")
public class Empresa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="empresa_id")	
	private Long EmpresaId;
	
	@NotEmpty
	@Column(name="empresa_nombre")
	private String EmpresaNombre;
	
	@NotEmpty
	@Column(name="empresa_rfc")
	private String EmpresaRfc;
	
	@NotEmpty
	@Column(name="empresa_razon_social")
	private String EmpresaRazonSocial;
	
	
	
	@NotEmpty
	@Column(name="empresa_correo")
	private String EmpresaCorreo;
	
	@NotEmpty
	@Column(name="empresa_tel")
	private String EmpresaTel;
	

	@Column(name="id_direccion")
	private Long IdDireccion;
	
	
	public Long getEmpresaId() {
		return EmpresaId;
	}
	public void setEmpresaId(Long empresaId) {
		EmpresaId = empresaId;
	}
	public String getEmpresaNombre() {
		return EmpresaNombre;
	}
	public void setEmpresaNombre(String empresaNombre) {
		EmpresaNombre = empresaNombre;
	}
	public String getEmpresaRfc() {
		return EmpresaRfc;
	}
	public void setEmpresaRfc(String empresaRfc) {
		EmpresaRfc = empresaRfc;
	}
	public String getEmpresaRazonSocial() {
		return EmpresaRazonSocial;
	}
	public void setEmpresaRazonSocial(String empresaRazonSocial) {
		EmpresaRazonSocial = empresaRazonSocial;
	}
	public String getEmpresaTel() {
		return EmpresaTel;
	}
	public void setEmpresaTel(String empresaTel) {
		EmpresaTel = empresaTel;
	}
	public Long getIdDireccion() {
		return IdDireccion;
	}
	public void setIdDireccion(Long idDireccion) {
		IdDireccion = idDireccion;
	}
	public String getEmpresaCorreo() {
		return EmpresaCorreo;
	}
	public void setEmpresaCorreo(String empresaCorreo) {
		EmpresaCorreo = empresaCorreo;
	}
	
	
	
}
