package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "datos_factura_paciente")
public class DatosFactura implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paciente_id")
	private Long PacienteId;

	@Column(name = "rfc")
	@Length(max = 13)
	private String Rfc;

	@Column(name = "email")
	@Length(max = 80)
	@Email
	@NotEmpty
	private String Email;

	@Column(name = "tel_celular")
	
	private Long TelCel;

	@Column(name = "direccion_calle")
	@NotEmpty
	@Length(max = 100)
	private String Calle;

	@Column(name = "codigo_postal")
	
	private Long CP;

	@Column(name = "direccion_colonia")
	@NotEmpty
	@Length(max = 100)
	private String Colonia;

	@Column(name = "direccion_municipio")
	@NotEmpty
	@Length(max = 100)
	private String Municipio;

	@Column(name = "direccion_estado")
	@NotEmpty
	@Length(max = 100)
	private String Estado;

	@Column(name = "nombre_empresa")

	@Length(max = 100)
	private String NombreEmpresa;

	@Column(name = "razon_social")
	@Length(max = 100)
	private String RazonSocial;
	/*
	 * @Column(name="tel_casa")
	 * 
	 * private Long TelCasa;
	 * 
	 * @Column(name="tel_empresa")
	 * 
	 * private Long TelEmpresa;
	 * 
	 * @Column(name="tel_exten")
	 * 
	 * private Long TelExten;
	 */

	/*
	 * @Column(name="nombre_empresa")
	 * 
	 * @Length(max=100)
	 * 
	 * private String NombreEmpresa;
	 * 
	 * @Column(name="razon_social")
	 * 
	 * private String RazonSocial;
	 */

	/****************************************************************************************/

	public Long getPacienteId() {
		return PacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		PacienteId = pacienteId;
	}

	public String getRfc() {
		return Rfc;
	}

	public void setRfc(String rfc) {
		Rfc = rfc;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public Long getTelCel() {
		return TelCel;
	}

	public void setTelCel(Long telCel) {
		TelCel = telCel;
	}

	public String getCalle() {
		return Calle;
	}

	public void setCalle(String calle) {
		Calle = calle;
	}

	public Long getCP() {
		return CP;
	}

	public void setCP(Long cP) {
		CP = cP;
	}

	public String getColonia() {
		return Colonia;
	}

	public void setColonia(String colonia) {
		Colonia = colonia;
	}

	public String getMunicipio() {
		return Municipio;
	}

	public void setMunicipio(String municipio) {
		Municipio = municipio;
	}

	public String getEstado() {
		return Estado;
	}

	public void setEstado(String estado) {
		Estado = estado;
	}

	public String getNombreEmpresa() {
		return NombreEmpresa;
	}

	public void setNombreEmpresa(String nombreEmpresa) {
		NombreEmpresa = nombreEmpresa;
	}

	public String getRazonSocial() {
		return RazonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		RazonSocial = razonSocial;
	}

	/*
	 * public Long getTelCasa() { return TelCasa; }
	 * 
	 * public void setTelCasa(Long telCasa) { TelCasa = telCasa; }
	 * 
	 * public Long getTelEmpresa() { return TelEmpresa; }
	 * 
	 * public void setTelEmpresa(Long telEmpresa) { TelEmpresa = telEmpresa; }
	 * 
	 * public Long getTelExten() { return TelExten; }
	 * 
	 * public void setTelExten(Long telExten) { TelExten = telExten; }
	 * 
	 * public String getNombreEmpresa() { return NombreEmpresa; }
	 * 
	 * public void setNombreEmpresa(String nombreEmpresa) { NombreEmpresa =
	 * nombreEmpresa; }
	 * 
	 * public String getRazonSocial() { return RazonSocial; }
	 * 
	 * public void setRazonSocial(String razonSocial) { RazonSocial = razonSocial; }
	 */

}
