package com.bemedica.springboot.app.models.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTables;
import javax.persistence.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "paciente_vista")
///@SecondaryTables({
   /// @SecondaryTable(name="persona", pkJoinColumns={
      //  @PrimaryKeyJoinColumn(name="direccion_id", referencedColumnName="direccion_id") })
//})
public class VistaPaciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   
	private Long direccion_id;
	private String direccion_estado;
	private String direccion_municipio;
	private String direccion_colonia;
	private String direccion_postal;
	private String direccion_calle;
	private String direccion_numero_ext;
	private String direccion_num_inter;
	private Long persona_id;
	private String persona_nombre;
	private String persona_ap;
	private String persona_am;
	private String persona_fecha_na;
	private String persona_rfc;
	private String persona_genero;
	private String persona_email;
	private String persona_tel_casa;
	private String persona_tel_cel;
	private String persona_tel_oficina;
	private String persona_tel_exten;
	private Long paciente_id;
	private String paciente_id_tex;
	private String observacion;
	private String empresa_id;
	
	public Long getDireccion_id() {
		return direccion_id;
	}
	public void setDireccion_id(Long direccion_id) {
		this.direccion_id = direccion_id;
	}
	public String getDireccion_estado() {
		return direccion_estado;
	}
	public void setDireccion_estado(String direccion_estado) {
		this.direccion_estado = direccion_estado;
	}
	public String getDireccion_municipio() {
		return direccion_municipio;
	}
	public void setDireccion_municipio(String direccion_municipio) {
		this.direccion_municipio = direccion_municipio;
	}
	public String getDireccion_colonia() {
		return direccion_colonia;
	}
	public void setDireccion_colonia(String direccion_colonia) {
		this.direccion_colonia = direccion_colonia;
	}
	public String getDireccion_postal() {
		return direccion_postal;
	}
	public void setDireccion_postal(String direccion_postal) {
		this.direccion_postal = direccion_postal;
	}
	public String getDireccion_calle() {
		return direccion_calle;
	}
	public void setDireccion_calle(String direccion_calle) {
		this.direccion_calle = direccion_calle;
	}
	public String getDireccion_numero_ext() {
		return direccion_numero_ext;
	}
	public void setDireccion_numero_ext(String direccion_numero_ext) {
		this.direccion_numero_ext = direccion_numero_ext;
	}
	public String getDireccion_num_inter() {
		return direccion_num_inter;
	}
	public void setDireccion_num_inter(String direccion_num_inter) {
		this.direccion_num_inter = direccion_num_inter;
	}
	public Long getPersona_id() {
		return persona_id;
	}
	public void setPersona_id(Long persona_id) {
		this.persona_id = persona_id;
	}
	public String getPersona_nombre() {
		return persona_nombre;
	}
	public void setPersona_nombre(String persona_nombre) {
		this.persona_nombre = persona_nombre;
	}
	public String getPersona_ap() {
		return persona_ap;
	}
	public void setPersona_ap(String persona_ap) {
		this.persona_ap = persona_ap;
	}
	public String getPersona_am() {
		return persona_am;
	}
	public void setPersona_am(String persona_am) {
		this.persona_am = persona_am;
	}
	public String getPersona_fecha_na() {
		return persona_fecha_na;
	}
	public void setPersona_fecha_na(String persona_fecha_na) {
		this.persona_fecha_na = persona_fecha_na;
	}
	public String getPersona_rfc() {
		return persona_rfc;
	}
	public void setPersona_rfc(String persona_rfc) {
		this.persona_rfc = persona_rfc;
	}
	public String getPersona_genero() {
		return persona_genero;
	}
	public void setPersona_genero(String persona_genero) {
		this.persona_genero = persona_genero;
	}
	public String getPersona_email() {
		return persona_email;
	}
	public void setPersona_email(String persona_email) {
		this.persona_email = persona_email;
	}
	public String getPersona_tel_casa() {
		return persona_tel_casa;
	}
	public void setPersona_tel_casa(String persona_tel_casa) {
		this.persona_tel_casa = persona_tel_casa;
	}
	public String getPersona_tel_cel() {
		return persona_tel_cel;
	}
	public void setPersona_tel_cel(String persona_tel_cel) {
		this.persona_tel_cel = persona_tel_cel;
	}
	public String getPersona_tel_oficina() {
		return persona_tel_oficina;
	}
	public void setPersona_tel_oficina(String persona_tel_oficina) {
		this.persona_tel_oficina = persona_tel_oficina;
	}
	public String getPersona_tel_exten() {
		return persona_tel_exten;
	}
	public void setPersona_tel_exten(String persona_tel_exten) {
		this.persona_tel_exten = persona_tel_exten;
	}
	public Long getPaciente_id() {
		return paciente_id;
	}
	public void setPaciente_id(Long paciente_id) {
		this.paciente_id = paciente_id;
	}
	public String getPaciente_id_tex() {
		return paciente_id_tex;
	}
	public void setPaciente_id_tex(String paciente_id_tex) {
		this.paciente_id_tex = paciente_id_tex;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public String getEmpresa_id() {
		return empresa_id;
	}
	public void setEmpresa_id(String empresa_id) {
		this.empresa_id = empresa_id;
	}
	

	
	

}
