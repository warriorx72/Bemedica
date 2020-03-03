package com.bemedica.springboot.app.models.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class WebUser implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO, generator="native")
	@GenericGenerator(name="native",strategy="native")
	private Long user_id;
	
	/*@Column 
	@NotBlank
	private String firstName;
	
	@Column 
	@NotBlank
	private String lastName;
	
	@Column(unique = true) 
	@Email 
	@NotBlank
	private String email; 
	*/
	@Column(name="paciente_id")
	private Integer paciente;

	@Column(unique = true)
	@NotBlank
	private String user_name;
	
	@Column
	private String user_status;
	
	@Column
	@NotBlank
	@NotNull
	private String user_password;
	
	
	@Column
	@NotBlank
	@NotNull
	private String tipo;
	
	
	

	@Column
	@NotBlank
	@NotNull
	private String extra;
	
	

	
	public String getExtra() {
		return extra;
	}

	public void setExtra(String extra) {
		this.extra = extra;
	}

	public String getUser_password() {
		return user_password;
	}

	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	@ManyToMany(fetch = FetchType.LAZY
            )
	@JoinTable(name="web_user_roles"
		,joinColumns=@JoinColumn(name="user_id")
		,inverseJoinColumns=@JoinColumn(name="role_id"))
	
	
	
	
	
	private Set<WebRole> webroles= new HashSet<>();


	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Integer getPaciente_id() {
		return paciente;
	}

	public void setPaciente_id(Integer paciente_id) {
		this.paciente = paciente_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getUser_status() {
		return user_status;
	}

	public void setUser_status(String user_status) {
		this.user_status = user_status;
	}

	public String getUserPassword() {
		return user_password;
	}

	public void setUserPassword(String user_password) {
		this.user_password = user_password;
	}

	
	public Set<WebRole> getWebroles() {
		return webroles;
	}

	public void setWebroles(Set<WebRole> webroles) {
		this.webroles = webroles;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((extra == null) ? 0 : extra.hashCode());
		result = prime * result + ((paciente == null) ? 0 : paciente.hashCode());
		result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
		result = prime * result + ((user_password == null) ? 0 : user_password.hashCode());
		result = prime * result + ((user_status == null) ? 0 : user_status.hashCode());
		result = prime * result + ((webroles == null) ? 0 : webroles.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WebUser other = (WebUser) obj;
		if (extra == null) {
			if (other.extra != null)
				return false;
		} else if (!extra.equals(other.extra))
			return false;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		if (tipo == null) {
			if (other.tipo != null)
				return false;
		} else if (!tipo.equals(other.tipo))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		if (user_password == null) {
			if (other.user_password != null)
				return false;
		} else if (!user_password.equals(other.user_password))
			return false;
		if (user_status == null) {
			if (other.user_status != null)
				return false;
		} else if (!user_status.equals(other.user_status))
			return false;
		if (webroles == null) {
			if (other.webroles != null)
				return false;
		} else if (!webroles.equals(other.webroles))
			return false;
		return true;
	}





	
	
}
