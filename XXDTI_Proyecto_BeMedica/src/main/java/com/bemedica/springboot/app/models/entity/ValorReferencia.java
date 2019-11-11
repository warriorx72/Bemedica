package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="valores_referencia")
public class ValorReferencia implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="valor_refe_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ValorRefeId;
	
	@Column(name="valor_refe_id_text")
	private String ValorRefeIdText;
	
	@Column(name="sexo")
	private String Sexo;
	
	@Column(name="referencia")
	private String Referencia;

	@Column(name="edad_inferior")
	private String EdadInferior;
	
	@Column(name="edad_superior")
	private String EdadSuperior;
	
	@Column(name="p1")
	private String P1;
	
	@Column(name="p2")
	private String P2;
	
	@Column(name="p3")
	private String P3;
	
	@Column(name="p4")
	private String P4;
	
	@Column(name="p5")
	private String P5;
	
	@Column(name="p6")
	private String P6;
	
	@Column(name="p7")
	private String P7;
	
	@Column(name="cualitativo")
	private String Cualitativo;
	
	@Column(name="variantes")
	private String Variantes;
	
	@Column(name="estudio_id")
	private Long EstudioId;

	public Long getValorRefeId() {
		return ValorRefeId;
	}

	public void setValorRefeId(Long valorRefeId) {
		ValorRefeId = valorRefeId;
	}

	public String getValorRefeIdText() {
		return ValorRefeIdText;
	}

	public void setValorRefeIdText(String valorRefeIdText) {
		ValorRefeIdText = valorRefeIdText;
	}

	public String getSexo() {
		return Sexo;
	}

	public void setSexo(String sexo) {
		Sexo = sexo;
	}

	public String getReferencia() {
		return Referencia;
	}

	public void setReferencia(String referencia) {
		Referencia = referencia;
	}



	public String getEdadInferior() {
		return EdadInferior;
	}

	public void setEdadInferior(String edadInferior) {
		EdadInferior = edadInferior;
	}

	public String getEdadSuperior() {
		return EdadSuperior;
	}

	public void setEdadSuperior(String edadSuperior) {
		EdadSuperior = edadSuperior;
	}

	public String getP1() {
		return P1;
	}

	public void setP1(String p1) {
		P1 = p1;
	}

	public String getP2() {
		return P2;
	}

	public void setP2(String p2) {
		P2 = p2;
	}

	public String getP3() {
		return P3;
	}

	public void setP3(String p3) {
		P3 = p3;
	}

	public String getP4() {
		return P4;
	}

	public void setP4(String p4) {
		P4 = p4;
	}

	public String getP5() {
		return P5;
	}

	public void setP5(String p5) {
		P5 = p5;
	}

	public String getP6() {
		return P6;
	}

	public void setP6(String p6) {
		P6 = p6;
	}

	public String getP7() {
		return P7;
	}

	public void setP7(String p7) {
		P7 = p7;
	}

	public String getCualitativo() {
		return Cualitativo;
	}

	public void setCualitativo(String cualitativo) {
		Cualitativo = cualitativo;
	}

	public String getVariantes() {
		return Variantes;
	}

	public void setVariantes(String variantes) {
		Variantes = variantes;
	}

	public Long getEstudioId() {
		return EstudioId;
	}

	public void setEstudioId(Long estudioId) {
		EstudioId = estudioId;
	}
	
}
