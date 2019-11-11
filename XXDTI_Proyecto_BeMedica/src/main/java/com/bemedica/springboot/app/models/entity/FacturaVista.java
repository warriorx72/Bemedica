package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "factura_vista")
public class FacturaVista implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="factura_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Length(max = 10)
	private Long FacturaId;
	
	@NotNull
	@Column(name="No_Folio")
	@Length(max = 10)
	private String NoFactura;
	
	@Column(name="Fecha")
	@Temporal(TemporalType.DATE)
	private Date Fecha;
	
	@Column(name="RFC")
	@Length(max = 10)
	private String Rfc;
	
	@Column(name="Razon_Social")
	@Length(max = 50)
	private String RazonSocial;
	
	@NotNull
	@Column(name="Monto")
	@Length(max = 20)
	private String MontoTotal;
	
	@NotNull
	@Column(name="STATUS")
	@Length(max = 50)
	private String Status;
	
	/****************************************************************************************/
	
	public Long getFacturaId() {
		return FacturaId;
	}

	public void setFacturaId(Long facturaId) {
		FacturaId = facturaId;
	}

	public String getNoFactura() {
		return NoFactura;
	}

	public void setNoFactura(String noFactura) {
		NoFactura = noFactura;
	}

	public Date getFecha() {
		return Fecha;
	}

	public void setFecha(Date fecha) {
		Fecha = fecha;
	}

	public String getRfc() {
		return Rfc;
	}

	public void setRfc(String rfc) {
		Rfc = rfc;
	}

	public String getRazonSocial() {
		return RazonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		RazonSocial = razonSocial;
	}

	public String getMontoTotal() {
		return MontoTotal;
	}

	public void setMontoTotal(String montoTotal) {
		MontoTotal = montoTotal;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
	
}
