package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.format.annotation.DateTimeFormat;



@Entity
@Table(name="persona")
public class PersonaE  implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "persona_id")
	private Long PersonaId;
	
	
	@Pattern(regexp="[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+")
	@NotEmpty
	@Column(name = "persona_nombre")
	private String PersonaNombre;
	
	@Pattern(regexp="[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+")
	@NotEmpty
	@Column(name = "persona_ap")
	private String PersonaAp;
	
	@Pattern(regexp="[a-zA-ZñÑáéíóúÁÉÍÓÚ\\s]+")
	@NotEmpty
	@Column(name = "persona_am")
	private String PersonaAm;
	
	@NotNull
	@Column(name = "persona_fecha_na")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date PersonaFechaNa;
	
	
	@Column(name = "persona_rfc")
	private String PersonaRfc;
	
	@NotEmpty
	@Column(name = "persona_genero")
	private String PersonaGenero;
	
	
	@Column(name = "persona_email")
	private String PersonaEmail;
	
	
	@Column(name = "persona_tel_casa")
	private String PersonaTelCasa;
	
	
	
	@Column(name = "persona_tel_oficina")
	private String PersonaTelOficina;
	
	
	@NotEmpty
	@Column(name = "persona_tel_cel")
	private String PersonaTelCel;
	

	@Column(name = "persona_tel_exten")
	private String PersonaTelExten;
	
	@Column(name = "id_direccion")
	private Integer IdDireccion;

	public Long getPersonaId() {
		return PersonaId;
	}

	public void setPersonaId(Long personaId) {
		PersonaId = personaId;
	}

	public String getPersonaNombre() {
		return PersonaNombre;
	}

	public void setPersonaNombre(String personaNombre) {
		PersonaNombre = personaNombre;
	}

	public String getPersonaAp() {
		return PersonaAp;
	}

	public void setPersonaAp(String personaAp) {
		PersonaAp = personaAp;
	}

	public String getPersonaAm() {
		return PersonaAm;
	}

	public void setPersonaAm(String personaAm) {
		PersonaAm = personaAm;
	}

	public Date getPersonaFechaNa() {
		return PersonaFechaNa;
	}

	public void setPersonaFechaNa(Date personaFechaNa) {
		PersonaFechaNa = personaFechaNa;
	}

	public String getPersonaRfc() {
		return PersonaRfc;
	}

	public void setPersonaRfc(String personaRfc) {
		PersonaRfc = personaRfc;
	}

	public String getPersonaGenero() {
		return PersonaGenero;
	}

	public void setPersonaGenero(String personaGenero) {
		PersonaGenero = personaGenero;
	}

	public String getPersonaEmail() {
		return PersonaEmail;
	}

	public void setPersonaEmail(String personaEmail) {
		PersonaEmail = personaEmail;
	}

	public String getPersonaTelCasa() {
		return PersonaTelCasa;
	}
	
	
	public void setPersonaTelCasa(String personaTelCasa) {
		PersonaTelCasa = personaTelCasa;
	}

	public String getPersonaTelOficina() {
		return PersonaTelOficina;
	}

	public void setPersonaTelOficina(String personaTelOficina) {
		PersonaTelOficina = personaTelOficina;
	}

	public String getPersonaTelExten() {
		return PersonaTelExten;
	}

	public void setPersonaTelExten(String personaTelExten) {
		PersonaTelExten = personaTelExten;
	}

	public Integer getIdDireccion() {
		return IdDireccion;
	}

	public void setIdDireccion(Integer idDireccion) {
		IdDireccion = idDireccion;
	}

	public String getPersonaTelCel() {
		return PersonaTelCel;
	}

	public void setPersonaTelCel(String personaTelCel) {
		PersonaTelCel = personaTelCel;
	}
	
	

}
