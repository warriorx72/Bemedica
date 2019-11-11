package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="paquetes")
public class Paquetes implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="paquete_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long PaqueteId;
	
	@Column(name="paquete_id_text")
	private String PaqueteIdText;
	
	@Column(name="paquete_nombre")
	private String PaqueteNombre;
	
	@Column(name="paquete_descripcion")
	private String PaqueteDescripcion;
	
	@Column(name="paquete_costo")
	private Long PaqueteCosto;
	
	@Column(name="bemedica_id")
	private int BeMedicaId;
	
	@Column(name="paquete_indicaciones_pa")
	private String PaqueteIndicacionesPa;
	
	@Column(name="paquete_indicaciones_pe")
	private String PaqueteIndicacionesPe;
	
	@Column(name="paquete_estatus")
	private boolean PaqueteEstatus;


	public boolean isPaqueteEstatus() {
		return PaqueteEstatus;
	}

	public void setPaqueteEstatus(boolean paqueteEstatus) {
		PaqueteEstatus = paqueteEstatus;
	}

	public Long getPaqueteId() {
		return PaqueteId;
	}

	public void setPaqueteId(Long paqueteId) {
		PaqueteId = paqueteId;
	}

	public String getPaqueteIdText() {
		return PaqueteIdText;
	}

	public void setPaqueteIdText(String paqueteIdText) {
		PaqueteIdText = paqueteIdText;
	}

	public String getPaqueteNombre() {
		return PaqueteNombre;
	}

	public void setPaqueteNombre(String paqueteNombre) {
		PaqueteNombre = paqueteNombre;
	}

	public String getPaqueteDescripcion() {
		return PaqueteDescripcion;
	}

	public void setPaqueteDescripcion(String paqueteDescripcion) {
		PaqueteDescripcion = paqueteDescripcion;
	}

	public Long getPaqueteCosto() {
		return PaqueteCosto;
	}

	public void setPaqueteCosto(Long paqueteCosto) {
		PaqueteCosto = paqueteCosto;
	}

	public int getBeMedicaId() {
		return BeMedicaId;
	}

	public void setBeMedicaId(int beMedicaId) {
		BeMedicaId = beMedicaId;
	}

	public String getPaqueteIndicacionesPa() {
		return PaqueteIndicacionesPa;
	}

	public void setPaqueteIndicacionesPa(String paqueteIndicacionesPa) {
		PaqueteIndicacionesPa = paqueteIndicacionesPa;
	}

	public String getPaqueteIndicacionesPe() {
		return PaqueteIndicacionesPe;
	}

	public void setPaqueteIndicacionesPe(String paqueteIndicacionesPe) {
		PaqueteIndicacionesPe = paqueteIndicacionesPe;
	}
	
	
	

}
