package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="caja_vista")
public class CajaVista implements Serializable{
	
		private static final long serialVersionUID = 1L;
		
	
		
		@Column(name="fecha_inicial")
		private String FechaInicial;

		@Column(name="fecha_final")
		private String FechaFinal;
		
		@Column(name="Sucursal")
		private String Nombre;
		
		@Id
		@Column(name="Monto")
		private String Monto;
		

		@Column(name="Realizo")
		private String Realizo;
		


		public String getRealizo() {
			return Realizo;
		}

		public void setRealizo(String realizo) {
			Realizo = realizo;
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

	
		public String getMonto() {
			return Monto;
		}

		public void setMonto(String monto) {
			Monto = monto;
		}

		public String getNombre() {
			return Nombre;
		}

		public void setNombre(String nombre) {
			Nombre = nombre;
		}
	}