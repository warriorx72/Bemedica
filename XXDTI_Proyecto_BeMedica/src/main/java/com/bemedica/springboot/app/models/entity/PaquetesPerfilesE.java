package com.bemedica.springboot.app.models.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="paquetes_perfiles")
public class PaquetesPerfilesE implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "paque_perfil_id")
	private Long PaquePerfilId;
	
	
	@NotEmpty
	@Column(name = "paque_perfil_nombre")
	private String PaquePerfilNombre;
	
	@NotEmpty
	@Column(name = "descripcion")
	private String Descripcion;

	@Column(name = "bemedica_id")
	private Long BemedicaId;
	
	@NotEmpty
	@Column(name = "paque_perfil_tipo")
	private String PaquePerfilTipo;
	
	
	@NotNull
	@Column(name = "paque_perfil_costo")
	private Double PaquePerfilCosto;

	public Long getPaquePerfilId() {
		return PaquePerfilId;
	}

	public void setPaquePerfilId(Long paquePerfilId) {
		PaquePerfilId = paquePerfilId;
	}

	public String getPaquePerfilNombre() {
		return PaquePerfilNombre;
	}

	public void setPaquePerfilNombre(String paquePerfilNombre) {
		PaquePerfilNombre = paquePerfilNombre;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public Long getBemedicaId() {
		return BemedicaId;
	}

	public void setBemedicaId(Long bemedicaId) {
		BemedicaId = bemedicaId;
	}

	public String getPaquePerfilTipo() {
		return PaquePerfilTipo;
	}

	public void setPaquePerfilTipo(String paquePerfilTipo) {
		PaquePerfilTipo = paquePerfilTipo;
	}

	public Double getPaquePerfilCosto() {
		return PaquePerfilCosto;
	}

	public void setPaquePerfilCosto(Double paquePerfilCosto) {
		PaquePerfilCosto = paquePerfilCosto;
	}


	
	
	

}
