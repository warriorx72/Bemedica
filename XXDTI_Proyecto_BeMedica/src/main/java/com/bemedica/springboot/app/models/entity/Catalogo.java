package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="catalogo")
public class Catalogo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="catalogo_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CatalogoId;
	
	@Column(name="nombre_cat")
	private String NombreCat;
	
	@Column(name="nombre")
	private String Nombre;

	public Long getCatalogoId() {
		return CatalogoId;
	}

	public void setCatalogoId(Long catalogoId) {
		CatalogoId = catalogoId;
	}

	public String getNombreCat() {
		return NombreCat;
	}

	public void setNombreCat(String nombreCat) {
		NombreCat = nombreCat;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}


}
