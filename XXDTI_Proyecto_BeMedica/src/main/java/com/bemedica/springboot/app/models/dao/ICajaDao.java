package com.bemedica.springboot.app.models.dao;
//import java.util.Date;
import java.util.List;
import com.bemedica.springboot.app.models.entity.Caja;

public interface ICajaDao {

	List<Caja> findAll();
	
	public void save(Caja caja); 
	
	public Caja findOne(Long CajaId);
	
	public String findLastCajaId();
	
	public String caja (Long id);
	
	public boolean cajaTipo();
	
	public String findTotalEfectivo(Long id);
	
	public String findTotalTarjeta(Long id);
	
	public boolean corteTipo();
	
	public String cierreCajaEfectivo(Long Id);
	
	public String cierreCajaTarjeta(Long Id);
}