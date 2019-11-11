package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="anti_catalogos")
public class AntiCatalogos implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="anti_catalogo_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long AntiCatalogoId;
	
	@Column(name="anti_id")
	private Long AntiId;
	
	@Column(name="catalogo_id")
	private String CatalogoId;

	public Long getAntiCatalogoId() {
		return AntiCatalogoId;
	}

	public void setAntiCatalogoId(Long antiCatalogoId) {
		AntiCatalogoId = antiCatalogoId;
	}

	public Long getAntiId() {
		return AntiId;
	}

	public void setAntiId(Long antiId) {
		AntiId = antiId;
	}

	public String getCatalogoId() {
		return CatalogoId;
	}

	public void setCatalogoId(String catalogoId) {
		CatalogoId = catalogoId;
	}
	
	
	
	

}
