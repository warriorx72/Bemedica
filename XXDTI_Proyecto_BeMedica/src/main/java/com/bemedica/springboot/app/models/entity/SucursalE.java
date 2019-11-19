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
	
	@NotEmpty
	@Column(name="sucursal_nombre")
	private String SucursalNombre;
	
	@NotEmpty
	@Column(name="direccion_id")
	private Integer DireccionId;

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

	public Integer getDireccionId() {
		return DireccionId;
	}

	public void setDireccionId(Integer direccionId) {
		DireccionId = direccionId;
	}
	
	
	

}
