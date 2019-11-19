package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="pp_estudios")
public class PpEstudios  implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "pp_id")
	private Long PpId;
	
	@Column(name = "paque_perfil_id")
	private Long PaquePerfilId;
	
	@Column(name = "estudio_id")
	private Long EstudioId;

	public Long getPpId() {
		return PpId;
	}

	public void setPpId(Long ppId) {
		PpId = ppId;
	}

	public Long getPaquePerfilId() {
		return PaquePerfilId;
	}

	public void setPaquePerfilId(Long paquePerfilId) {
		PaquePerfilId = paquePerfilId;
	}

	public Long getEstudioId() {
		return EstudioId;
	}

	public void setEstudioId(Long estudioId) {
		EstudioId = estudioId;
	}
	
	
	
	

}
