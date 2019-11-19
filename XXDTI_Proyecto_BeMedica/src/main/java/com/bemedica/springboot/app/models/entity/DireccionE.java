package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;



@Entity
@Table(name="direccion")
public class DireccionE  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "direccion_id")
	private Long DireccionId;
	
	@NotEmpty 
	@Column(name = "direccion_estado")
	private String DireccionEstado;
	
	@NotEmpty
	@Column(name = "direccion_municipio")
	private String DireccionMunicipio;
	
	@NotEmpty
	@Column(name = "direccion_colonia")
	private String DireccionColonia;
	
	
	@Size(min = 5, max = 5)
	@NotEmpty
	@Column(name = "direccion_postal")
	private String DireccionPostal;
	
	@NotEmpty
	@Column(name = "direccion_calle")
	private String DireccionCalle;
	
	@NotEmpty
	@Pattern(regexp="^[0-9]+")
	@Column(name = "direccion_numero_ext")
	private String DireccionNumeroExt;
	
	@Column(name = "direccion_num_inter")
	private String DireccionNumeroInter;

	public Long getDireccionId() {
		return DireccionId;
	}

	public void setDireccionId(Long direccionId) {
		DireccionId = direccionId;
	}

	public String getDireccionEstado() {
		return DireccionEstado;
	}

	public void setDireccionEstado(String direccionEstado) {
		DireccionEstado = direccionEstado;
	}

	public String getDireccionMunicipio() {
		return DireccionMunicipio;
	}

	public void setDireccionMunicipio(String direccionMunicipio) {
		DireccionMunicipio = direccionMunicipio;
	}

	public String getDireccionColonia() {
		return DireccionColonia;
	}

	public void setDireccionColonia(String direccionColonia) {
		DireccionColonia = direccionColonia;
	}
	
	

	public String getDireccionPostal() {
		return DireccionPostal;
	}

	public void setDireccionPostal(String direccionPostal) {
		DireccionPostal = direccionPostal;
	}

	public String getDireccionCalle() {
		return DireccionCalle;
	}

	public void setDireccionCalle(String direccionCalle) {
		DireccionCalle = direccionCalle;
	}

	public String getDireccionNumeroExt() {
		return DireccionNumeroExt;
	}

	public void setDireccionNumeroExt(String direccionNumeroExt) {
		DireccionNumeroExt = direccionNumeroExt;
	}

	public String getDireccionNumeroInter() {
		return DireccionNumeroInter;
	}

	public void setDireccionNumeroInter(String direccionNumInter) {
		DireccionNumeroInter = direccionNumInter;
	}
	
}
