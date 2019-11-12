package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;
//import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name ="caja_vista")
public class CajaVista implements Serializable{
	
		private static final long serialVersionUID = 1L;
		
	
		
		@Column(name="fecha_inicial")
		@Temporal(TemporalType.TIMESTAMP)
		private Date FechaInicial;

		@Column(name="fecha_final")
		@Temporal(TemporalType.TIMESTAMP)
		private Date FechaFinal;
		
		@Column(name="Sucursal")
		private String Nombre;
		
		@Id
		@Column(name="caja_id")
		private Long CajaId;
		
		@Column(name="Monto")
		private Long Monto;
		

		@Column(name="Realizo")
		private String Realizo;

		@Column(name="Tipo")
		private String Tipo;
		/******************************************************************************/
		
		
		
		
		public String getNombre() {
			return Nombre;
		}
		
		public Date getFechaInicial() {
			return FechaInicial;
		}

		public void setFechaInicial(Date fechaInicial) {
			FechaInicial = fechaInicial;
		}

		public Date getFechaFinal() {
			return FechaFinal;
		}

		public void setFechaFinal(Date fechaFinal) {
			FechaFinal = fechaFinal;
		}

		public void setNombre(String nombre) {
			Nombre = nombre;
		}

		

		public Long getMonto() {
			return Monto;
		}


		public void setMonto(Long monto) {
			Monto = monto;
		}


		public String getRealizo() {
			return Realizo;
		}


		public void setRealizo(String realizo) {
			Realizo = realizo;
		}

		public Long getCajaId() {
			return CajaId;
		}

		public void setCajaId(Long cajaId) {
			CajaId = cajaId;
		}

		public String getTipo() {
			return Tipo;
		}

		public void setTipo(String tipo) {
			Tipo = tipo;
		}
	}