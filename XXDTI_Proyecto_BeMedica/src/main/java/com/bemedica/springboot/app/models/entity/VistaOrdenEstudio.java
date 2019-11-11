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
@Table(name = "orden_estudios_vista")
///@SecondaryTables({
   /// @SecondaryTable(name="persona", pkJoinColumns={
      //  @PrimaryKeyJoinColumn(name="direccion_id", referencedColumnName="direccion_id") })
//})
public class VistaOrdenEstudio implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="orden_id")
	private Long orden_id;
	@Column(name="estudio_nombre")
	private String estudio_nombre;
	@Column(name="estudio_precio")
	private String estudio_precio;
	///@Column(name="cantidad_estudio")
	///private String cantidad_estudio;
	@Id
	private Long orden_estudio_id;
	public Long getOrden_estudio_id() {
		return orden_estudio_id;
	}
	public void setOrden_estudio_id(Long orden_estudio_id) {
		this.orden_estudio_id = orden_estudio_id;
	}
	@Column(name="total_linea")
	private String total_linea;
	public Long getOrden_id() {
		return orden_id;
	}
	public void setOrden_id(Long orden_id) {
		this.orden_id = orden_id;
	}
	public String getEstudio_nombre() {
		return estudio_nombre;
	}
	public void setEstudio_nombre(String estudio_nombre) {
		this.estudio_nombre = estudio_nombre;
	}
	public String getEstudio_precio() {
		return estudio_precio;
	}
	public void setEstudio_precio(String estudio_precio) {
		this.estudio_precio = estudio_precio;
	}
	///public String getCantidad_estudio() {
		//return cantidad_estudio;
	//}
	//public void setCantidad_estudio(String cantidad_estudio) {
		//this.cantidad_estudio = cantidad_estudio;
	//}
	public String getTotal_linea() {
		return total_linea;
	}
	public void setTotal_linea(String total_linea) {
		this.total_linea = total_linea;
	}
	
	

	
	

}
