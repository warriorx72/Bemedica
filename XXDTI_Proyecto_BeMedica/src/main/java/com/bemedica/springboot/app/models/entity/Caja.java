package com.bemedica.springboot.app.models.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name ="caja")
public class Caja implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="caja_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long CajaId;
	
	
	@Column(name="fecha_inicial")	
	private String FechaInicial;

	@Column(name="fecha_final")
	private String FechaFinal;

	@Column(name="monto_efectivo")
	private String MontoEfectivo;
	
	@Column(name="monto_tarjeta")
	private String MontoTarjeta;
	
	@Column(name="caja_chica")
	private String CajaChica;
	
	@Column(name="corte_tipo")
	private boolean CorteTipo;	
	
	public String getMontoEfectivo() {
		return MontoEfectivo;
	}

	public void setMontoEfectivo(String montoEfectivo) {
		MontoEfectivo = montoEfectivo;
	}

	public String getMontoTarjeta() {
		return MontoTarjeta;
	}

	public void setMontoTarjeta(String montoTarjeta) {
		MontoTarjeta = montoTarjeta;
	}

	public String getCajaChica() {
		return CajaChica;
	}

	public void setCajaChica(String cajaChica) {
		CajaChica = cajaChica;
	}

	public boolean isCorteTipo() {
		return CorteTipo;
	}

	public void setCorteTipo(boolean corteTipo) {
		CorteTipo = corteTipo;
	}

	public Long getCajaId() {
		return CajaId;
	}

	public void setCajaId(Long cajaId) {
		CajaId = cajaId;
	}

	public String getFechaInicial() {
		return FechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		FechaInicial = fechaInicial;
	}

	public String getFechaFinal() {
		return FechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		FechaFinal = fechaFinal;
	}


}