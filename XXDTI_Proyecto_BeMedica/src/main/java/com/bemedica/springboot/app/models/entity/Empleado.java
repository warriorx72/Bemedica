package com.bemedica.springboot.app.models.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import javax.validation.constraints.NotEmpty;
import javax.persistence.Temporal;


import javax.persistence.Table;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;


import java.util.Date;



@Entity
@Table(name="empleado")
public class Empleado  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "empleado_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long empleado_id;

	
	@Column(name = "empleado_id_text")
	private String empleado_id_text;
	
	@NotEmpty
	@Column(name = "empleado_ap")
	private String empleado_ap;
	
	@NotEmpty
	@Column(name = "empleado_am")
	private String empleado_am;
	
	@NotEmpty
	@Column(name = "empleado_nombre")
	private String empleado_nombre;
	
	
	@Column(name="empleado_fecha_na")
	@Temporal (TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date empleado_fecha_na;
	
	@NotEmpty
	@Column(name = "empleado_sexo")
	private String empleado_sexo;
	
	
	@NotEmpty
	@Column(name="empleado_estado")
	private String empleado_estado;
	
	@NotEmpty
	@Column(name="empleado_municipio")
	private String empleado_municipio;
	
	@NotEmpty
	@Column(name = "empleado_localidad")
	private String empleado_localidad;
	
	@NotEmpty
	@Column(name="empleado_postal")
	private String empleado_postal;
	
	@NotEmpty
	@Column(name = "empleado_calle")
	private String empleado_calle;
	
	@NotEmpty
	@Column(name = "empleado_numero")
	private String empleado_numero;
	
	@NotEmpty
	@Column(name = "empleado_tel_oficina")
	private String empleado_tel_oficina;
	
	@NotEmpty
	@Column(name = "empleado_extension")
	private String empleado_extension;
	
	@NotEmpty
	@Column(name = "empleado_tel_casa")
	private String empleado_tel_casa;
	
	@NotEmpty
	@Length(min = 10, max= 10)
	
	@Column(name = "empleado_celular")
	private String empleado_celular;
	
	@NotEmpty
	@Column(name = "empleado_no_afiliacion")
	private String empleado_no_afiliacion;
	
	public String getEmpleado_extension() {
		return empleado_extension;
	}





	public void setEmpleado_extension(String empleado_extension) {
		this.empleado_extension = empleado_extension;
	}





	public String getEmpleado_no_afiliacion() {
		return empleado_no_afiliacion;
	}





	public void setEmpleado_no_afiliacion(String empleado_no_afiliacion) {
		this.empleado_no_afiliacion = empleado_no_afiliacion;
	}





	public static long getSerialversionuid() {
		return serialVersionUID;
	}





	@NotEmpty
	@Column(name = "empleado_cedula")
	private String empleado_cedula;
	

	@Column(name = "empleado_sueldo")
	private Double   empleado_sueldo;
	
	@NotEmpty
	@Column(name = "empleado_email")
	private String empleado_email;
	
	@NotEmpty
	@Column (name = "empleado_rfc")
	private String empleado_rfc;
	
	
	@Column(name= "empleado_fecha_creacion")
	@Temporal (TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date empleado_fecha_creacion;
	
	
	
	
	
	public Long getEmpleado_id() {
		return empleado_id;
	}





	public void setEmpleado_id(Long empleado_id) {
		this.empleado_id = empleado_id;
	}





	public String getEmpleado_id_text() {
		return empleado_id_text;
	}





	public void setEmpleado_id_text(String empleado_id_text) {
		this.empleado_id_text = empleado_id_text;
	}





	public String getEmpleado_ap() {
		return empleado_ap;
	}





	public void setEmpleado_ap(String empleado_ap) {
		this.empleado_ap = empleado_ap;
	}





	public String getEmpleado_am() {
		return empleado_am;
	}





	public void setEmpleado_am(String empleado_am) {
		this.empleado_am = empleado_am;
	}





	public String getEmpleado_nombre() {
		return empleado_nombre;
	}





	public void setEmpleado_nombre(String empleado_nombre) {
		this.empleado_nombre = empleado_nombre;
	}





	public Date getEmpleado_fecha_na() {
		return empleado_fecha_na;
	}





	public void setEmpleado_fecha_na(Date empleado_fecha_na) {
		this.empleado_fecha_na = empleado_fecha_na;
	}





	public String getEmpleado_sexo() {
		return empleado_sexo;
	}





	public void setEmpleado_sexo(String empleado_sexo) {
		this.empleado_sexo = empleado_sexo;
	}





	public String getEmpleado_estado() {
		return empleado_estado;
	}





	public void setEmpleado_estado(String empleado_estado) {
		this.empleado_estado = empleado_estado;
	}





	public String getEmpleado_municipio() {
		return empleado_municipio;
	}





	public void setEmpleado_municipio(String empleado_municipio) {
		this.empleado_municipio = empleado_municipio;
	}





	public String getEmpleado_localidad() {
		return empleado_localidad;
	}





	public void setEmpleado_localidad(String empleado_localidad) {
		this.empleado_localidad = empleado_localidad;
	}





	public String getEmpleado_postal() {
		return empleado_postal;
	}





	public void setEmpleado_postal(String empleado_postal) {
		this.empleado_postal = empleado_postal;
	}





	public String getEmpleado_calle() {
		return empleado_calle;
	}





	public void setEmpleado_calle(String empleado_calle) {
		this.empleado_calle = empleado_calle;
	}





	public String getEmpleado_numero() {
		return empleado_numero;
	}





	public void setEmpleado_numero(String empleado_numero) {
		this.empleado_numero = empleado_numero;
	}





	public String getEmpleado_tel_oficina() {
		return empleado_tel_oficina;
	}





	public void setEmpleado_tel_oficina(String empleado_tel_oficina) {
		this.empleado_tel_oficina = empleado_tel_oficina;
	}





	public String getempleado_extension() {
		return empleado_extension;
	}





	public void setempleado_extension(String empleado_extension) {
		this.empleado_extension = empleado_extension;
	}





	public String getEmpleado_tel_casa() {
		return empleado_tel_casa;
	}





	public void setEmpleado_tel_casa(String empleado_tel_casa) {
		this.empleado_tel_casa = empleado_tel_casa;
	}





	public String getEmpleado_celular() {
		return empleado_celular;
	}





	public void setEmpleado_celular(String empleado_celular) {
		this.empleado_celular = empleado_celular;
	}





	public String getempleado_no_afiliacion() {
		return empleado_no_afiliacion;
	}





	public void setempleado_no_afiliacion(String empleado_no_afiliacion) {
		this.empleado_no_afiliacion = empleado_no_afiliacion;
	}





	public String getEmpleado_cedula() {
		return empleado_cedula;
	}





	public void setEmpleado_cedula(String empleado_cedula) {
		this.empleado_cedula = empleado_cedula;
	}





	public Double getEmpleado_sueldo() {
		return empleado_sueldo;
	}





	public void setEmpleado_sueldo(Double empleado_sueldo) {
		this.empleado_sueldo = empleado_sueldo;
	}





	public String getEmpleado_email() {
		return empleado_email;
	}





	public void setEmpleado_email(String empleado_email) {
		this.empleado_email = empleado_email;
	}





	public String getEmpleado_rfc() {
		return empleado_rfc;
	}





	public void setEmpleado_rfc(String empleado_rfc) {
		this.empleado_rfc = empleado_rfc;
	}





	public Date getEmpleado_fecha_creacion() {
		return empleado_fecha_creacion;
	}





	public void setEmpleado_fecha_creacion(Date empleado_fecha_creacion) {
		this.empleado_fecha_creacion = empleado_fecha_creacion;
	}





	@PrePersist
	public void preFechaIngreso (){
		empleado_fecha_creacion= new Date();
	}
	
	

}
