
package com.bemedica.springboot.app.models.dao;

import java.sql.Timestamp;
//import java.text.DateFormat;
//import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
//import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Caja;

@Repository
public class CajaDaoImpl implements ICajaDao {
	
	@PersistenceContext
	private EntityManager em;
	@Transactional(readOnly =true)
	@SuppressWarnings("unchecked")
	@Override
	
	public List<Caja> findAll(int num1) {
	return em.createQuery("From Caja").getResultList();
	}
	   
	@Override
	@Transactional
	public void save(Caja caja) {
		if(caja.getCajaId() != null && caja.getCajaId() >0) {
			em.merge(caja);
		}else {
			em.persist(caja);	
		}
	}

	//-----------------------------
	@Override
	@Transactional(readOnly =true)
	public Caja findOne(Long CajaId) {
		// TODO Auto-generated method stub
		return em.find(Caja.class, CajaId);
	}
    //-----------------------------
	@Override
	@Transactional
	public String findLastCajaId(Long id) {
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// TODO Auto-generated method stub
		String auxs = em.createNativeQuery("SELECT fecha_final FROM caja \r\n" + 
				"WHERE caja_id = (SELECT MAX(caja_id) FROM caja \r\n" + 
				"	WHERE sucursal_id ="+id+")").getSingleResult().toString();
		
		return auxs ;
	}		
	@Transactional(readOnly=true)
	@Override	
	public String caja(Long id) {
		
		Timestamp  Timestamp  =  (java.sql.Timestamp) em.createNativeQuery("SELECT caja.fecha_final from caja where caja.caja_id="+id).getSingleResult();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		String string  = dateFormat.format(Timestamp);
		return string ;
	}

	@Override
	@Transactional
	public boolean cajaTipo() {
		String tipo =(em.createNativeQuery ("Select IF((Select count(*) FROM caja)=0,1,(SELECT corte_tipo FROM caja ORDER BY caja_id DESC LIMIT 1)) ").getSingleResult().toString());
		boolean x;
		if(tipo.equals("1")) {
			x=true;
			return x;
		}
		else {
			x=false;
			return x;
		}
		
	}

	@Override
	@Transactional
	public String findTotalEfectivo(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("call TotalEfectivo("+id+")").getSingleResult().toString();
	}
	
	@Override
	@Transactional
	public String findTotalTarjeta(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("call TotalTarjeta("+id+")").getSingleResult().toString();
	}
	

	@Override
	@Transactional
	public boolean corteTipo(Long id) {
		String tipo =(em.createNativeQuery ("SELECT IFNULL(\r\n" + 
				"	(SELECT IFNULL(\r\n" + 
				"		(SELECT corte_tipo FROM caja \r\n" + 
				"				WHERE fecha_final = ( SELECT fecha_final FROM caja \r\n" + 
				"				WHERE caja_id = (SELECT MAX(caja_id) FROM caja \r\n" + 
				"				WHERE sucursal_id ="+id+")) \r\n" + 
				"					AND corte_tipo = 1\r\n" + 
				"				ORDER BY\r\n" + 
				"					caja_id DESC),\r\n" + 
				"				(SELECT corte_tipo \r\n" + 
				"				FROM caja \r\n" + 
				"				WHERE fecha_final = (SELECT fecha_final FROM caja \r\n" + 
				"				WHERE caja_id = (SELECT MAX(caja_id) FROM caja \r\n" + 
				"				WHERE sucursal_id = "+id+")) \r\n" + 
				"					AND corte_tipo = 0 \r\n" + 
				"				ORDER BY\r\n" + 
				"					caja_id DESC ))), 1)").getSingleResult().toString());
		boolean x;
		if(tipo.equals("1")) {
			x=true;
			return x;
		}
		else {
			x=false;
			return x;
		}	
	}
	@Override
	@Transactional
	public String cierreCajaEfectivo(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("call TotalEfectivo("+id+")").getSingleResult().toString();
	}
	
	@Override
	@Transactional
	public String cierreCajaTarjeta(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("call TotalTarjeta("+id+")").getSingleResult().toString();
	}
	
	@Override
	@Transactional
	public Integer Autoincrement(Long id) {
		// TODO Auto-generated method stub
		return Integer.parseInt(em.createNativeQuery("SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_NAME='caja' AND TABLE_SCHEMA=DATABASE();").getSingleResult().toString());
	}
	@Override
	@Transactional(readOnly =true)
	public boolean  bloqueoCorte(Long id) {
		// TODO Auto-generated method stub
		 boolean cierre=(Integer.parseInt(em.createNativeQuery(" call bloqueo_Corte("+id+")").getSingleResult().toString())==1);	
		return  cierre;	
	}
	
	
}