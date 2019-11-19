package com.bemedica.springboot.app.models.dao;


import java.util.List;




public interface IReportes {
	
	public List<Object[]> estudios_ventas(String f1 , String f2, String p);
	
	
	public List<Object[]> Resultados_imprimir(Long id);
	
	
	public List<Object[]> DatosPaciente(Long id);
	

}
