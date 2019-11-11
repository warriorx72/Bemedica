package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

//import javax.validation.constraints.Email;
//import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

//import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "factura")
public class Factura implements Serializable {

	
//	HOLA CHRIS 
	// Uriel was here
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "factura_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long FacturaId;


	@Column(name = "factura_id_text")
	@Length(max = 10)
	@NotNull
	private String FacturaIdText;

	@Column(name = "fecha_factura")
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date CreateAt;

	@PrePersist
	public void prePersist() {
		CreateAt = new Date();
	}

	@Column(name = "status")
	@NotNull
	@Length(max = 50)
	private String Status;

	@Column(name = "orden_id")
	@NotNull
	private Long OrdenId;

	/*******************************************************************************/
	
	public Long getFacturaId() {
		return FacturaId;
	}

	public void setFacturaId(Long facturaId) {
		FacturaId = facturaId;
	}

	public String getFacturaIdText() {
		return FacturaIdText;
	}

	public void setFacturaIdText(String facturaIdText) {
		FacturaIdText = facturaIdText;
	}

	public Date getCreateAt() {
		return CreateAt;
	}

	public void setCreateAt(Date createAt) {
		CreateAt = createAt;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}

	public Long getOrdenId() {
		return OrdenId;
	}

	public void setOrdenId(Long ordenId) {
		OrdenId = ordenId;
	}

}
