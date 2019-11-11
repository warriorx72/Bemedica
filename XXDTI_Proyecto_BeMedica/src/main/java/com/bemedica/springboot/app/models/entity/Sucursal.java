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
@Table(name = "sucursal")
///@SecondaryTables({
   /// @SecondaryTable(name="persona", pkJoinColumns={
      //  @PrimaryKeyJoinColumn(name="direccion_id", referencedColumnName="direccion_id") })
//})
public class Sucursal implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	///private Long id;
	
	public Long sucursal_id;
	public String sucursal_nombre;
	public Long getSucursal_id() {
		return sucursal_id;
	}
	public void setSucursal_id(Long sucursal_id) {
		this.sucursal_id = sucursal_id;
	}
	public String getSucursal_nombre() {
		return sucursal_nombre;
	}
	public void setSucursal_nombre(String sucursal_nombre) {
		this.sucursal_nombre = sucursal_nombre;
	}
	
}
