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

@Entity
@Table(name = "pagos_medico_vista")
public class PagosVista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_pago")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long IdPago;
	
	@Column(name="id_medico")
	private int IdMedico;

	@Column(name="id_text")
	private String IdText;
	
	@Column(name="Nombre_Medico")
	private String NombreMedico;
	
	@Column(name="Pago")
	private int MontoPago;
	
	@Column(name="Fecha")
	@Temporal(TemporalType.DATE)
	private Date FechaPago;
	
	/*******************************************************************************************/

	public Long getIdPago() {
		return IdPago;
	}

	public void setIdPago(Long idPago) {
		IdPago = idPago;
	}
	
	public String getIdText() {
		return IdText;
	}

	public void setIdText(String idText) {
		IdText = idText;
	}

	public int getIdMedico() {
		return IdMedico;
	}

	public void setIdMedico(int idMedico) {
		IdMedico = idMedico;
	}

	public String getNombreMedico() {
		return NombreMedico;
	}

	public void setNombreMedico(String nombreMedico) {
		NombreMedico = nombreMedico;
	}

	public int getMontoPago() {
		return MontoPago;
	}

	public void setMontoPago(int montoPago) {
		MontoPago = montoPago;
	}

	public Date getFechaPago() {
		return FechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		FechaPago = fechaPago;
	}
	
	
}
