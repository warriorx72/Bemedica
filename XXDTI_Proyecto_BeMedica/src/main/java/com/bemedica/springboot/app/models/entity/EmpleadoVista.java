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
@Table(name="empleado_vista")
public class EmpleadoVista implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empleado_id")
	private Long EmpleadoId;
	
	@NotEmpty 
	@Column(name = "empleado_id_text")
	private String EmpleadoIdText;
	
	@NotEmpty
	@Column(name = "persona_nombre")
	private String PersonaNombre;
	
	@NotEmpty
	@Column(name = "persona_ap")
	private String PersonaAp;
	
	@NotEmpty
	@Column(name = "persona_am")
	private String PersonaAm;
	
	public String getPersonaAm() {
		return PersonaAm;
	}

	public void setPersonaAm(String personaAm) {
		PersonaAm = personaAm;
	}

	@NotEmpty
	@Column(name = "persona_email")
	private String PersonaEmail;
	
	@NotEmpty
	@Column(name = "persona_tel_cel")
	private String PersonaTelCel;
	
	@NotEmpty
	@Column(name = "sucursal_nombre")
	private String SucursalNombre;
	
	@NotNull
	@Column(name = "empleado_estatus")
	private String EmpleadoEstatus;

	public Long getEmpleadoId() {
		return EmpleadoId;
	}

	public void setEmpleadoId(Long empleadoId) {
		EmpleadoId = empleadoId;
	}

	public String getEmpleadoIdText() {
		return EmpleadoIdText;
	}

	public void setEmpleadoIdText(String empleadoIdText) {
		EmpleadoIdText = empleadoIdText;
	}

	public String getPersonaNombre() {
		return PersonaNombre;
	}

	public void setPersonaNombre(String personaNombre) {
		PersonaNombre = personaNombre;
	}

	public String getPersonaAp() {
		return PersonaAp;
	}

	public void setPersonaAp(String personaAp) {
		PersonaAp = personaAp;
	}

	public String getPersonaTelCel() {
		return PersonaTelCel;
	}

	public void setPersonaTelCel(String personaTelCel) {
		PersonaTelCel = personaTelCel;
	}

	public String getSucursalNombre() {
		return SucursalNombre;
	}

	public void setSucursalNombre(String sucursalNombre) {
		SucursalNombre = sucursalNombre;
	}

	public String getPersonaEmail() {
		return PersonaEmail;
	}

	public void setPersonaEmail(String personaEmail) {
		PersonaEmail = personaEmail;
	}

	public String getEmpleadoEstatus() {
		return EmpleadoEstatus;
	}

	public void setEmpleadoEstatus(String empleadoEstatus) {
		EmpleadoEstatus = empleadoEstatus;
	}
	
	

}
