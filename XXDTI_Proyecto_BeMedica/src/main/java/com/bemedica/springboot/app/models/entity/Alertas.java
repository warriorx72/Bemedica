package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;

@Entity
@Table(name = "alertas")
public class Alertas implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="alerta_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long AlertaId;
	
	@Column(name="normal")
	private Long Normal;
	
	@Column(name="rango_1")
	@Length(max=80)
	private String Rango1;
	
	@Column(name="rango_2")
	@Length(max=80)
	private String Rango2;
	
	@Column(name="rango_3")
	@Length(max=80)
	private String Rango3;
	
	@Column(name="rango_4")
	@Length(max=80)
	private String Rango4;
	
	@Column(name="rango_5")
	@Length(max=80)
	private String Rango5;
	
	@Column(name="rango_6")
	@Length(max=80)
	private String Rango6;
	
	@Column(name="rango_7")
	@Length(max=80)
	private String Rango7;
	
	@Column(name="rango_8")
	@Length(max=80)
	private String Rango8;
	
	@Column(name="img_1")
	@Length(max=80)
	private String Img1;
	
	@Column(name="img_2")
	@Length(max=80)
	private String Img2;
	
	@Column(name="img_3")
	@Length(max=80)
	private String Img3;
	
	@Column(name="img_4")
	@Length(max=80)
	private String Img4;
	
	@Column(name="img_5")
	@Length(max=80)
	private String Img5;
	
	@Column(name="img_6")
	@Length(max=80)
	private String Img6;
	
	@Column(name="img_7")
	@Length(max=80)
	private String Img7;
	
	@Column(name="img_8")
	@Length(max=80)
	private String Img8;
	
	/*****************************************************************************************************/

	public Long getAlertaId() {
		return AlertaId;
	}

	public void setAlertaId(Long alertaId) {
		AlertaId = alertaId;
	}

	public Long getNormal() {
		return Normal;
	}

	public void setNormal(Long normal) {
		Normal = normal;
	}

	public String getRango1() {
		return Rango1;
	}

	public void setRango1(String rango1) {
		Rango1 = rango1;
	}

	public String getRango2() {
		return Rango2;
	}

	public void setRango2(String rango2) {
		Rango2 = rango2;
	}

	public String getRango3() {
		return Rango3;
	}

	public void setRango3(String rango3) {
		Rango3 = rango3;
	}

	public String getRango4() {
		return Rango4;
	}

	public void setRango4(String rango4) {
		Rango4 = rango4;
	}

	public String getRango5() {
		return Rango5;
	}

	public void setRango5(String rango5) {
		Rango5 = rango5;
	}

	public String getRango6() {
		return Rango6;
	}

	public void setRango6(String rango6) {
		Rango6 = rango6;
	}

	public String getRango7() {
		return Rango7;
	}

	public void setRango7(String rango7) {
		Rango7 = rango7;
	}

	public String getRango8() {
		return Rango8;
	}

	public void setRango8(String rango8) {
		Rango8 = rango8;
	}

	public String getImg1() {
		return Img1;
	}

	public void setImg1(String img1) {
		Img1 = img1;
	}

	public String getImg2() {
		return Img2;
	}

	public void setImg2(String img2) {
		Img2 = img2;
	}

	public String getImg3() {
		return Img3;
	}

	public void setImg3(String img3) {
		Img3 = img3;
	}

	public String getImg4() {
		return Img4;
	}

	public void setImg4(String img4) {
		Img4 = img4;
	}

	public String getImg5() {
		return Img5;
	}

	public void setImg5(String img5) {
		Img5 = img5;
	}

	public String getImg6() {
		return Img6;
	}

	public void setImg6(String img6) {
		Img6 = img6;
	}

	public String getImg7() {
		return Img7;
	}

	public void setImg7(String img7) {
		Img7 = img7;
	}

	public String getImg8() {
		return Img8;
	}

	public void setImg8(String img8) {
		Img8 = img8;
	}
	
}
