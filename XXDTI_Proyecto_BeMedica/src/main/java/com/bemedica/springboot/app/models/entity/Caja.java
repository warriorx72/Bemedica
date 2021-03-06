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
	public Long CajaId;
	
	@Column(name="fecha_inicial")	
	private String FechaInicial;

	@Column(name="fecha_final")
	private String FechaFinal;

	@Column(name="monto_efectivo")
	private String MontoEfectivo;
	
	@Column(name="monto_tarjeta")
	private String MontoTarjeta;
	
	@Column(name="monto_contado")
	private String MontoContado;
	
	@Column(name="descripcion_caja")
	private String DescripcionCaja;

	@Column(name="corte_tipo")
	private boolean CorteTipo;	
	
	@Column(name="sucursal_id")
	private String IdSucursal;
	
	@Column(name="user_Id")
	private String user_Id;
	
	public String getMontoEfectivo() {
		return MontoEfectivo;
	}
	
	public void setMontoEfectivo(String montoEfectivo) {
		MontoEfectivo = montoEfectivo;
	}
	public String getIdSucursal() {
		return IdSucursal;
	}

	public void setIdSucursal(String idSucursal) {
		IdSucursal =idSucursal;
	}

	
	public String getUser_Id() {
		return user_Id;
	}

	public void setUser_Id(String user_Id) {
		this.user_Id = user_Id;
	}

	public String getMontoTarjeta() {
		return MontoTarjeta;
	}

	public void setMontoTarjeta(String montoTarjeta) {
		MontoTarjeta = montoTarjeta;
	}

	public String getMontoContado() {
		return MontoContado;
	}

	public void setMontoContado(String montoContado) {
		MontoContado = montoContado;
	}
	
	public String getDescripcionCaja() {
		return DescripcionCaja;
	}
	
	public void setDescripcionCaja(String descripcionCaja) {
		DescripcionCaja = descripcionCaja;
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
	
	public void setbloqueoCorte() {
		
	}
    public void setcancelar_monto(Long cajaId) {
		
	}
    
    public void setlistar() {
    	
    }

}