package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "paciente")
public class Pacientes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="paciente_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PacienteId;
	
	@Column(name="persona_id")
	@NotNull
	private Long PersonaId;
	
	@Column(name="paciente_id_tex")
	@NotNull
	@Length(max = 10)
	private String PacienteIdText;
	
	@Column(name="observacion")
	@NotNull
	private String Observacion;
	
	@Column(name="empresa_id")
	private Long EmpresaId;
	
	@Column(name="direccion_id")
	private Long DireccionId;
	
	/*******************************************************************************************************/

	public Long getPacienteId() {
		return PacienteId;
	}

	public void setPacienteId(Long pacienteId) {
		PacienteId = pacienteId;
	}

	public Long getPersonaId() {
		return PersonaId;
	}

	public void setPersonaId(Long personaId) {
		PersonaId = personaId;
	}

	public String getPacienteIdText() {
		return PacienteIdText;
	}

	public void setPacienteIdText(String pacienteIdText) {
		PacienteIdText = pacienteIdText;
	}

	public String getObservacion() {
		return Observacion;
	}

	public void setObservacion(String observacion) {
		Observacion = observacion;
	}

	public Long getEmpresaId() {
		return EmpresaId;
	}

	public void setEmpresaId(Long empresaId) {
		EmpresaId = empresaId;
	}

	public Long getDireccionId() {
		return DireccionId;
	}

	public void setDireccionId(Long direccionId) {
		DireccionId = direccionId;
	}
	
}
