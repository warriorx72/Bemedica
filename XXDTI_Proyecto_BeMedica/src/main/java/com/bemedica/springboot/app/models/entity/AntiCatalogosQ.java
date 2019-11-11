package com.bemedica.springboot.app.models.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class AntiCatalogosQ {
	
	@Id
	protected Long anti_catalogo_id;

	protected int anti_id;
	protected String nombre;
	public Long getAntiCatalogoId() {
		return anti_catalogo_id;
	}
	public void setAntiCatalogoId(Long antiCatalogoId) {
		anti_catalogo_id = antiCatalogoId;
	}
	public int getAntiId() {
		return anti_id;
	}
	public void setAntiId(int antiId) {
		anti_id = antiId;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre1) {
		nombre = nombre1;
	}

	
}
