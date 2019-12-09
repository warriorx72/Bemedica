package com.bemedica.springboot.app.models.entity;
import java.io.Serializable;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;


@Entity
@Table(name="sucursal")
public class SucursalE  implements Serializable{
	
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sucursal_id")
	private Long SucursalId;
	
	@Column(name="sucursal_id_text")
	private String SucusalIdText;
	
	@Column(name="sucursal_nombre")
	private String SucursalNombre;
	
	
	@Column(name="direccion_id")
	private Long DireccionId;
	
	
	@Column(name="estatus")
	private boolean estatus;
	
	
	@Column(name="sucursal_correo")
	private String SucursalCoreo;
	
	
	@Column(name="sucursal_telefono")
	private String SucursalTelefono;

	public Long getSucursalId() {
		return SucursalId;
	}

	public void setSucursalId(Long sucursalId) {
		SucursalId = sucursalId;
	}

	public String getSucursalNombre() {
		return SucursalNombre;
	}

	public void setSucursalNombre(String sucursalNombre) {
		SucursalNombre = sucursalNombre;
	}

	public Long getDireccionId() {
		return DireccionId;
	}

	public void setDireccionId(Long direccionId) {
		DireccionId = direccionId;
	}

	public String getSucursalCoreo() {
		return SucursalCoreo;
	}

	public void setSucursalCoreo(String sucursalCoreo) {
		SucursalCoreo = sucursalCoreo;
	}

	public String getSucursalTelefono() {
		return SucursalTelefono;
	}

	public void setSucursalTelefono(String sucursalTelefono) {
		SucursalTelefono = sucursalTelefono;
	}

	public boolean getEstatus() {
		return estatus;
	}

	public void setEstatus(boolean estatus) {
		this.estatus = estatus;
	}

	public String getSucusalIdText() {
		return SucusalIdText;
	}

	public void setSucusalIdText(String sucusalIdText) {
		SucusalIdText = sucusalIdText;
	}
	
}
