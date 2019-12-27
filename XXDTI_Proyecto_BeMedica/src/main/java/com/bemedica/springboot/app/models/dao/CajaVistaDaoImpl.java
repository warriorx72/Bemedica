package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public class CajaVistaDaoImpl implements ICajaVistaDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@Transactional(readOnly =true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Object []> findAll() {
	return em.createNativeQuery("call CorteCierre").getResultList();
	}
	
	@Transactional(readOnly =true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAll2(int num1, int num2) {
		return em.createNativeQuery("call ReporteCorteCierre("+num1+","+num2+")").getResultList();
	}

	@Transactional(readOnly =true)
	//@SuppressWarnings("unchecked")
	@Override
	public float findAll3(int num1, int num2) {
		String auxs = em.createNativeQuery("call TotalTotal("+num1+","+num2+")").getSingleResult().toString();
		float aux = Float.parseFloat(auxs);
		return aux;
	}

	@Transactional(readOnly =true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAll4(int num1, int num2) {
		return em.createNativeQuery("call Fechas("+num1+","+num2+")").getResultList();
	}

	@Transactional(readOnly =true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAll5(int num1, int num2) {
	
		return em.createNativeQuery("call TotalEfecTarje("+num1+","+num2+")").getResultList();	
	}

}