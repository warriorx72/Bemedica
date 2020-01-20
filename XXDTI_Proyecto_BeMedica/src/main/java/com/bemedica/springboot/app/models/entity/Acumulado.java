package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "acumulado_vista")
public class Acumulado implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_medico")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int IdMedico;
	
	@Column(name="id_text")	
	private String IdText;
	
	@Column(name="Nombre_Medico")	
	private String NombreMedico;
	
	@Column(name="Adeudo")
	private Long Adeudo;
	
	/*********************************************************************************************/

	public int getIdMedico() {
		return IdMedico;
	}

	public void setIdMedico(int idMedico) {
		IdMedico = idMedico;
	}
	

	public String getIdText() {
		return IdText;
	}

	public void setIdText(String idText) {
		IdText = idText;
	}

	public String getNombreMedico() {
		return NombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		NombreMedico = nombreMedico;
	}

	public Long getAdeudo() {
		return Adeudo;
	}

	public void setAdeudo(Long adeudo) {
		Adeudo = adeudo;
	}


	
}
