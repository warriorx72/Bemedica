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
@Table(name = "empleado_vista")
///@SecondaryTables({
   /// @SecondaryTable(name="persona", pkJoinColumns={
      //  @PrimaryKeyJoinColumn(name="direccion_id", referencedColumnName="direccion_id") })
//})
public class VistaEmpleado implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
   
	private Long empleado_id;
	private String persona_nombre;
	private String persona_ap;
	
	
	
	public Long getEmpleado_id() {
		return empleado_id;
	}
	public void setEmpleado_id(Long empleado_id) {
		this.empleado_id = empleado_id;
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
	
	
	

}
