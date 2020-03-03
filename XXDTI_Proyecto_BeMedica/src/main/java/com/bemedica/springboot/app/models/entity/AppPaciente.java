package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "app_paciente")
public class AppPaciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paciente_id;
	@Column
	private String expediente;
	@Column
	private String estado_civil;
	@Column
	private String paciente_referencia_nombre;
	@Column
	private String paciente_referencia_ap;
	@Column
	private String paciente_referencia_am;
	@Column
	private String paciente_referencia_tel;

	@Column
	private Long persona_id;

	public Long getPaciente_id() {
		return paciente_id;
	}

	public void setPaciente_id(Long paciente_id) {
		this.paciente_id = paciente_id;
	}

	public String getExpediente() {
		return expediente;
	}

	public void setExpediente(String expediente) {
		this.expediente = expediente;
	}

	public String getEstado_civil() {
		return estado_civil;
	}

	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}

	public String getPaciente_referencia_nombre() {
		return paciente_referencia_nombre;
	}

	public void setPaciente_referencia_nombre(String paciente_referencia_nombre) {
		this.paciente_referencia_nombre = paciente_referencia_nombre;
	}

	public String getPaciente_referencia_ap() {
		return paciente_referencia_ap;
	}

	public void setPaciente_referencia_ap(String paciente_referencia_ap) {
		this.paciente_referencia_ap = paciente_referencia_ap;
	}

	public String getPaciente_referencia_am() {
		return paciente_referencia_am;
	}

	public void setPaciente_referencia_am(String paciente_referencia_am) {
		this.paciente_referencia_am = paciente_referencia_am;
	}

	public String getPaciente_referencia_tel() {
		return paciente_referencia_tel;
	}

	public void setPaciente_referencia_tel(String paciente_referencia_tel) {
		this.paciente_referencia_tel = paciente_referencia_tel;
	}

	public Long getPersona_id() {
		return persona_id;
	}

	public void setPersona_id(Long persona_id) {
		this.persona_id = persona_id;
	}
}
