package com.bemedica.springboot.app.models.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="estudios_view")
public class EstudiosView {
	
	 
	@Column(name="id")
	private Long Id;
	@Id 
	@Column(name="id_text")
	private String IdText;
	
	@Column(name="nombre")
	private String Nombre;
	
	@Column(name="descripcion")
	private String Descripcion;
	@Column(name="catalogo")
	private String Catalogo;
	
	@Column(name="visible")
	private Boolean Visible;
	
	@Column(name="modificado")
	private String Modificado;
	
	@Column(name="modifico")
	private String Modifico;

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getIdText() {
		return IdText;
	}

	public void setIdText(String idText) {
		IdText = idText;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public String getDescripcion() {
		return Descripcion;
	}

	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}

	public String getCatalogo() {
		return Catalogo;
	}

	public void setCatalogo(String catalogo) {
		Catalogo = catalogo;
	}

	public Boolean getVisible() {
		return Visible;
	}

	public void setVisible(Boolean visible) {
		Visible = visible;
	}

	public String getModificado() {
		return Modificado;
	}

	public void setModificado(String modificado) {
		Modificado = modificado;
	}

	public String getModifico() {
		return Modifico;
	}

	public void setModifico(String modifico) {
		Modifico = modifico;
	}


	
	
	

}
