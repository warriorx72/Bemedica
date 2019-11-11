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
@Table(name = "prueba")
///@SecondaryTables({
   /// @SecondaryTable(name="persona", pkJoinColumns={
      //  @PrimaryKeyJoinColumn(name="direccion_id", referencedColumnName="direccion_id") })
//})
public class Prueba implements Serializable {

	private static final long serialVersionUID = 1L;

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
	///private Long id;
	
	public Long id_prueba;
	public String prueba_nombre;

	

	public Long getId_prueba() {
		return id_prueba;
	}

	public void setId_prueba(Long id_prueba) {
		this.id_prueba = id_prueba;
	}
	public String getPrueba_nombre() {
		return prueba_nombre;
	}

	public void setPrueba_nombre(String prueba_nombre) {
		this.prueba_nombre = prueba_nombre;
	}
}
