package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="promociones")
public class Promociones implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@Column(name="promocion_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PromocionId;
	
	@Column(name="	promocion_nombre")
	private String 	PromocionNombre;
	
	@Column(name="promocion_descripcion	")
	private String PromocionDescripcion	;

	@Column(name="promocion_descuento")
	private String PromocionDescuento;
	
	@Column(name="promocion_id_text")
	private String PromocionIdText;

	public String getPromocionIdText() {
		return PromocionIdText;
	}

	public void setPromocionIdText(String promocionIdText) {
		PromocionIdText = promocionIdText;
	}

	public Long getPromocionId() {
		return PromocionId;
	}

	public void setPromocionId(Long promocionId) {
		PromocionId = promocionId;
	}

	public String getPromocionNombre() {
		return PromocionNombre;
	}

	public void setPromocionNombre(String promocionNombre) {
		PromocionNombre = promocionNombre;
	}

	public String getPromocionDescripcion() {
		return PromocionDescripcion;
	}

	public void setPromocionDescripcion(String promocionDescripcion) {
		PromocionDescripcion = promocionDescripcion;
	}

	public String getPromocionDescuento() {
		return PromocionDescuento;
	}

	public void setPromocionDescuento(String promocionDescuento) {
		PromocionDescuento = promocionDescuento;
	}
	
	
	
}
