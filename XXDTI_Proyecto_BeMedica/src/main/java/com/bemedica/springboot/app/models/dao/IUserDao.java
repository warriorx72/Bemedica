package com.bemedica.springboot.app.models.dao;

import java.util.List;

import com.bemedica.springboot.app.models.entity.EmpleadoVista;
import com.bemedica.springboot.app.models.entity.User;

public interface IUserDao {

	public List<EmpleadoVista> ev();
	
	public void save (User user);
}
