package com.bemedica.springboot.app.models.dao;
//import java.util.Date;
import java.util.List;
import com.bemedica.springboot.app.models.entity.Caja;

public interface ICajaDao {

	List<Caja> findAll(int num1);
	
	public void save(Caja caja); 
	
	public Caja findOne(Long CajaId);
	
	public String findLastCajaId(Long id);
	
	public String caja (Long id);
	
	public boolean cajaTipo();
	
	public String findTotalEfectivo(Long id);
	
	public String findTotalTarjeta(Long id);
	
	public boolean corteTipo(Long id);
	
	public String cierreCajaEfectivo(Long Id);
	
	public String cierreCajaTarjeta(Long Id);
	
	public Integer Autoincrement(Long Id);

	public boolean bloqueoCorte(Long id);
	

}