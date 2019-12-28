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
		
		
		
		
		
	
	
			
}