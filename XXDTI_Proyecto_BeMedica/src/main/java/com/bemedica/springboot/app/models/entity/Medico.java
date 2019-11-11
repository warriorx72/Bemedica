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
@Table(name = "medicos")
///@SecondaryTables({
   /// @SecondaryTable(name="persona", pkJoinColumns={
      //  @PrimaryKeyJoinColumn(name="direccion_id", referencedColumnName="direccion_id") })
//})
public class Medico implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	///private Long id;
	
	public Long medico_id;
	public Long persona_id;
	public String medico_id_text;
	public String medico_foto;
	public String medico_especialidad;
	public String medico_cedula;
	
	
	
	public Long getMedico_id() {
		return medico_id;
	}
	public void setMedico_id(Long medico_id) {
		this.medico_id = medico_id;
	}
	public Long getPersona_id() {
		return persona_id;
	}
	public void setPersona_id(Long persona_id) {
		this.persona_id = persona_id;
	}
	public String getMedico_id_text() {
		return medico_id_text;
	}
	public void setMedico_id_text(String medico_id_text) {
		this.medico_id_text = medico_id_text;
	}
	
	
	public String getMedico_foto() {
		return medico_foto;
	}
	public void setMedico_foto(String medico_foto) {
		this.medico_foto = medico_foto;
	}
	public String getMedico_especialidad() {
		return medico_especialidad;
	}
	public void setMedico_especialidad(String medico_especialidad) {
		this.medico_especialidad = medico_especialidad;
	}
	public String getMedico_cedula() {
		return medico_cedula;
	}
	public void setMedico_cedula(String medico_cedula) {
		this.medico_cedula = medico_cedula;
	}
	
	
}
