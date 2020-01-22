//Isaac Rafael
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
	
	public List<Caja> findAll() {
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
	public String findLastCajaId() {
		 SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// TODO Auto-generated method stub
		String auxs = em.createQuery ("SELECT MAX(CajaId) FROM Caja").getSingleResult().toString();
		
		String auxs1 = em.createQuery ("SELECT FechaFinal FROM Caja where CajaId="+auxs+"").getSingleResult().toString();

		return auxs1 ;
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
	public boolean corteTipo() {
		String tipo =(em.createNativeQuery ("SELECT IFNULL((SELECT IFNULL((SELECT corte_tipo FROM caja WHERE fecha_final = (SELECT MAX(fecha_final) FROM caja) AND corte_tipo = 1 ORDER BY caja_id DESC),(SELECT corte_tipo FROM caja WHERE fecha_final = (SELECT MAX(fecha_final) FROM caja) AND corte_tipo = 0 ORDER BY caja_id DESC))),1)").getSingleResult().toString());
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
	public Boolean  bloqueaCorte() {
		// TODO Auto-generated method stub
		 boolean cierre=(Integer.parseInt(em.createNativeQuery(" call bloqueo_Corte()").getSingleResult().toString())==1);	
		return  cierre;	
	}

	@Override
	@Transactional(readOnly =true)
	public boolean  bloqueoCorte() {
		// TODO Auto-generated method stub
		 boolean cierre=(Integer.parseInt(em.createNativeQuery(" call bloqueo_Corte()").getSingleResult().toString())==1);	
		return  cierre;	
	}		
	
	
}