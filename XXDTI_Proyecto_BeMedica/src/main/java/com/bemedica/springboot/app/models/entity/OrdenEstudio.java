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
@Table(name = "orden_estudio")
///@SecondaryTables({
   /// @SecondaryTable(name="persona", pkJoinColumns={
      //  @PrimaryKeyJoinColumn(name="direccion_id", referencedColumnName="direccion_id") })
//})
public class OrdenEstudio implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	///private Long id;
	public Long orden_estudio_id;
	public Long getOrden_estudio_id() {
		return orden_estudio_id;
	}
	public void setOrden_estudio_id(Long orden_estudio_id) {
		this.orden_estudio_id = orden_estudio_id;
	}
	public Long orden_id;
	public Long estudio_id;
	////public int cantidad_estudio;
	public String tipo;

	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Long getOrden_id() {
		return orden_id;
	}
	public void setOrden_id(Long orden_id) {
		this.orden_id = orden_id;
	}
	public Long getEstudio_id() {
		return estudio_id;
	}
	public void setEstudio_id(Long estudio_id) {
		this.estudio_id = estudio_id;
	}
	///public int getCantidad_estudio() {
		///return cantidad_estudio;
	//}
	//public void setCantidad_estudio(int cantidad_estudio) {
		//this.cantidad_estudio = cantidad_estudio;
	//}
	
	
}
