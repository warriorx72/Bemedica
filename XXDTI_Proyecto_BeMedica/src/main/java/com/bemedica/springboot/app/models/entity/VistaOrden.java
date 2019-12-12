package com.bemedica.springboot.app.models.entity;
import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SecondaryTables;
import javax.persistence.*;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "orden_vista")
///@SecondaryTables({
   /// @SecondaryTable(name="persona", pkJoinColumns={
      //  @PrimaryKeyJoinColumn(name="direccion_id", referencedColumnName="direccion_id") })
//})
public class VistaOrden implements Serializable {

	private static final long serialVersionUID = 1L;

	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	@Id
	private Long orden_id;
	private String nombre;
	private String persona_ap;
	private String persona_am;
	private String orden_folio;
	private String orden_estatus;
	private String orden_fecha;
	private String monto;
	private String pago_inicial;
	private String adeudo;
	private String metodo_pago;
	private String pago_final;
	private String fecha_liquidacion;
	
	private String promocion_id;
	
	
	public String getMetodo_pago() {
		return metodo_pago;
	}
	public void setMetodo_pago(String metodo_pago) {
		this.metodo_pago = metodo_pago;
	}
	public String getPago_final() {
		return pago_final;
	}
	public void setPago_final(String pago_final) {
		this.pago_final = pago_final;
	}
	public String getFecha_liquidacion() {
		return fecha_liquidacion;
	}
	public void setFecha_liquidacion(String fecha_liquidacion) {
		this.fecha_liquidacion = fecha_liquidacion;
	}
	public Long getOrden_id() {
		return orden_id;
	}
	public void setOrden_id(Long orden_id) {
		this.orden_id = orden_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPersona_ap() {
		return persona_ap;
	}
	public void setPersona_ap(String persona_ap) {
		this.persona_ap = persona_ap;
	}
	public String getPersona_am() {
		return persona_am;
	}
	public void setPersona_am(String persona_am) {
		this.persona_am = persona_am;
	}
	public String getOrden_folio() {
		return orden_folio;
	}
	public void setOrden_folio(String orden_folio) {
		this.orden_folio = orden_folio;
	}
	public String getOrden_estatus() {
		return orden_estatus;
	}
	public void setOrden_estatus(String orden_estatus) {
		this.orden_estatus = orden_estatus;
	}
	public String getOrden_fecha() {
		return orden_fecha;
	}
	public void setOrden_fecha(String orden_fecha) {
		this.orden_fecha = orden_fecha;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
	public String getPago_inicial() {
		return pago_inicial;
	}
	public void setPago_inicial(String pago_inicial) {
		this.pago_inicial = pago_inicial;
	}
	public String getAdeudo() {
		return adeudo;
	}
	public void setAdeudo(String adeudo) {
		this.adeudo = adeudo;
	}
	public String getPromocion_id() {
		return promocion_id;
	}
	public void setPromocion_id(String promocion_id) {
		this.promocion_id = promocion_id;
	}
	
	

}
