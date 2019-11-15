package com.bemedica.springboot.app.models.dao;

import java.util.List;


public interface ICajaVistaDao {

	public List<Object []> findAll();
	
	public List<Object []> findAll2(int num1 , int num2);
	
	public float findAll3(int num1 , int num2);
	
}