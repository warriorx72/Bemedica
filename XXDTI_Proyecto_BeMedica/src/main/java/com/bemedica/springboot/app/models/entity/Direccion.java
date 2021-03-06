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
@Table(name = "direccion")
///@SecondaryTables({
   /// @SecondaryTable(name="persona", pkJoinColumns={
      //  @PrimaryKeyJoinColumn(name="direccion_id", referencedColumnName="direccion_id") })
//})
public class Direccion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	///private Long id;
	
	public Long direccion_id;
	private String direccion_estado;
	private String direccion_municipio;
	private String direccion_colonia;
	private String direccion_postal;
	private String direccion_calle;
	private String direccion_numero_ext;
	private String direccion_num_inter;
	
	
	
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
	
	
	


	
	

}
