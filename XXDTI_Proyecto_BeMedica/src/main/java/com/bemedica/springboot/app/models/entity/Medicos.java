package com.bemedica.springboot.app.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "medicos")
public class Medicos {

	@Column(name="medico_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long MedicoId;
	
	@Column(name="medico_id_text")
	@Length(max = 15)
	private String MedicoIdText;
	
	@Column(name="medico_foto")
	@Length(max = 100)
	private String MedicoFoto;
	
	@Column(name="medico_especialidad")
	@Length(max = 80)
	private String MedicoEspecialidad;
	
	@Column(name="medico_cedula")
	@Length(max = 20)
	private String MedicoCedula;
	
	@Column(name="persona_id")
	private Long PersonaId;
	
	@Column(name="bemedica_id")
	private Long BemedicaId;
	
	/*****************************************************************************************/

	public Long getMedicoId() {
		return MedicoId;
	}

	public void setMedicoId(Long medicoId) {
		MedicoId = medicoId;
	}

	public String getMedicoIdText() {
		return MedicoIdText;
	}

	public void setMedicoIdText(String medicoIdText) {
		MedicoIdText = medicoIdText;
	}

	public String getMedicoFoto() {
		return MedicoFoto;
	}

	public void setMedicoFoto(String medicoFoto) {
		MedicoFoto = medicoFoto;
	}

	public String getMedicoEspecialidad() {
		return MedicoEspecialidad;
	}

	public void setMedicoEspecialidad(String medicoEspecialidad) {
		MedicoEspecialidad = medicoEspecialidad;
	}

	public String getMedicoCedula() {
		return MedicoCedula;
	}

	public void setMedicoCedula(String medicoCedula) {
		MedicoCedula = medicoCedula;
	}

	public Long getPersonaId() {
		return PersonaId;
	}

	public void setPersonaId(Long personaId) {
		PersonaId = personaId;
	}

	public Long getBemedicaId() {
		return BemedicaId;
	}

	public void setBemedicaId(Long bemedicaId) {
		BemedicaId = bemedicaId;
	}
	
}
