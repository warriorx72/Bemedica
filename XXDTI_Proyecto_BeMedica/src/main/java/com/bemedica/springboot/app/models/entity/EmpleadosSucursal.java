package com.bemedica.springboot.app.models.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name="empleados_sucursal")
public class EmpleadosSucursal implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "empleado_id")
	private Long EmpleadoId;
	

	@Column(name = "empleado_id_text")
	private String EmpleadoIdText;
	
	
	@Column(name = "persona_id")
	private Integer PersonaId;
	
	
	@Size(min = 11, max = 11)
	@NotEmpty
	@Column(name = "afiliacion_imss")
	private String AfiliacionImss;
	

	@NotNull
	@Column(name = "empleado_sueldo")
	private Double EmpleadoSueldo;
	
	@NotEmpty
	@Column(name = "empleado_estatus")
	private String EmpleadoEstatus;
	
	
	@NotNull
	@Column(name = "sucursal_id")
	private Integer SucursalId;
	
	
		
	public Long getEmpleadoId() {
		return EmpleadoId;
	}

	public void setEmpleadoId(Long empleadoId) {
		EmpleadoId = empleadoId;
	}

	public String getEmpleadoIdText() {
		return EmpleadoIdText;
	}

	public void setEmpleadoIdText(String empleadoIdText) {
		EmpleadoIdText = empleadoIdText;
	}

	public Integer getPersonaId() {
		return PersonaId;
	}

	public void setPersonaId(Integer personaId) {
		PersonaId = personaId;
	}

	public String getAfiliacionImss() {
		return AfiliacionImss;
	}

	public void setAfiliacionImss(String afiliacionImss) {
		AfiliacionImss = afiliacionImss;
	}

	public Double getEmpleadoSueldo() {
		return EmpleadoSueldo;
	}

	public void setEmpleadoSueldo(Double empleadoSueldo) {
		EmpleadoSueldo = empleadoSueldo;
	}

	public Integer getSucursalId() {
		return SucursalId;
	}

	public void setSucursalId(Integer sucursalId) {
		SucursalId = sucursalId;
	}

	public String getEmpleadoEstatus() {
		return EmpleadoEstatus;
	}

	public void setEmpleadoEstatus(String empleadoEstatus) {
		EmpleadoEstatus = empleadoEstatus;
	}
	
	

}
