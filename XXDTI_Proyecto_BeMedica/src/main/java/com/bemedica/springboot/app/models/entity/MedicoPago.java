package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;
//import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "medico_pagos")
public class MedicoPago implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "medico_id_pago")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long mPId;
	
	@Column(name = "medico_id")
	@NotNull
	private Long MedicoId;
	
	@Column(name = "monto_pago")
	@NotNull
	private Long MontoPago;
	
	@NotNull
	@Column(name = "fecha_Pago")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date FechaPago;
	
	/*@PrePersist
	public void prePersist() {
		FechaPago = new Date();
	}*/
	
	/**************************************************************************************/

	public Long getmPId() {
		return mPId;
	}

	public void setmPId(Long mPId) {
		this.mPId = mPId;
	}

	public Long getMedicoId() {
		return MedicoId;
	}

	public void setMedicoId(Long medicoId) {
		MedicoId = medicoId;
	}

	public Long getMontoPago() {
		return MontoPago;
	}

	public void setMontoPago(Long montoPago) {
		MontoPago = montoPago;
	}

	public Date getFechaPago() {
		return FechaPago;
	}

	public void setFechaPago(Date fechaPago) {
		FechaPago = fechaPago;
	}


}
