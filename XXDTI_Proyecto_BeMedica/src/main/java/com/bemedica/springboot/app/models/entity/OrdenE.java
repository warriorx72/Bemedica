package com.bemedica.springboot.app.models.entity;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.*;


@Entity
@Table(name = "orden")
public class OrdenE implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	///private Long id;
	
	public Long orden_id;
	public String orden_folio;
	public Integer paciente_id;
	public String sucursal_id;
	public String empleado_id;
	public String orden_estatus;
	public String orden_fecha;
    public String orden_urgencia;
    public String orden_cotizacion;
    public String factura_id;
    public Integer medico_id;
    public String orden_confidencial;
    public String monto;
    public String pago_inicial;
	public String getPago_inicial() {
		return pago_inicial;
	}
	public void setPago_inicial(String pago_inicial) {
		this.pago_inicial = pago_inicial;
	}
	public Long getOrden_id() {
		return orden_id;
	}
	public void setOrden_id(Long orden_id) {
		this.orden_id = orden_id;
	}
	public String getOrden_folio() {
		return orden_folio;
	}
	public void setOrden_folio(String orden_folio) {
		this.orden_folio = orden_folio;
	}
	public Integer getPaciente_id() {
		return paciente_id;
	}
	public void setPaciente_id(Integer paciente_id) {
		this.paciente_id = paciente_id;
	}
	
	public String getSucursal_id() {
		return sucursal_id;
	}
	public void setSucursal_id(String sucursal_id) {
		this.sucursal_id = sucursal_id;
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
	public String getOrden_urgencia() {
		return orden_urgencia;
	}
	public void setOrden_urgencia(String orden_urgencia) {
		this.orden_urgencia = orden_urgencia;
	}
	public String getOrden_cotizacion() {
		return orden_cotizacion;
	}
	public void setOrden_cotizacion(String orden_cotizacion) {
		this.orden_cotizacion = orden_cotizacion;
	}
	public String getFactura_id() {
		return factura_id;
	}
	public void setFactura_id(String factura_id) {
		this.factura_id = factura_id;
	}
	public Integer getMedico_id() {
		return medico_id;
	}
	public void setMedico_id(Integer medico_id) {
		this.medico_id = medico_id;
	}
	public String getEmpleado_id() {
		return empleado_id;
	}
	public void setEmpleado_id(String empleado_id) {
		this.empleado_id = empleado_id;
	}
	public String getOrden_confidencial() {
		return orden_confidencial;
	}
	public void setOrden_confidencial(String orden_confidencial) {
		this.orden_confidencial = orden_confidencial;
	}
	public String getMonto() {
		return monto;
	}
	public void setMonto(String monto) {
		this.monto = monto;
	}
}