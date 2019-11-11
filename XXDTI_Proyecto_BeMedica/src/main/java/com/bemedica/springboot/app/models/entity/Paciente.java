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
@Table(name = "paciente")
///@SecondaryTables({
   /// @SecondaryTable(name="persona", pkJoinColumns={
      //  @PrimaryKeyJoinColumn(name="direccion_id", referencedColumnName="direccion_id") })
//})
public class Paciente implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	///private Long id;
	
	public Long paciente_id;
	public Long persona_id;
	public String paciente_id_tex;
	public String observacion;
	public String empresa_id;
	///private String direccion_estado;
	///private String direccion_municipio;
	//7private String direccion_colonia;
	//private String direccion_postal;
	//private String direccion_calle;
	//private String direccion_numero_ext;
	//private String direccion_num_inter;
	
	
	
	public Long getPaciente_id() {
		return paciente_id;
	}
	public void setPaciente_id(Long paciente_id) {
		this.paciente_id = paciente_id;
	}
	public Long getPersona_id() {
		return persona_id;
	}
	public void setPersona_id(Long persona_id) {
		this.persona_id = persona_id;
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
//	public String getDireccion_estado() {
	//	return direccion_estado;
	//}
	//public void setDireccion_estado(String direccion_estado) {
		//this.direccion_estado = direccion_estado;
	//}
	//public String getDireccion_municipio() {
		//return direccion_municipio;
	//}
	/*public void setDireccion_municipio(String direccion_municipio) {
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
	//}
	//public void setDireccion_numero_ext(String direccion_numero_ext) {
		//this.direccion_numero_ext = direccion_numero_ext;
	//}
	//public String getDireccion_num_inter() {
		//return direccion_num_inter;
	//}
	//public void setDireccion_num_inter(String direccion_num_inter) {
		//this.direccion_num_inter = direccion_num_inter;
	//}
	

*/
	
	

}
