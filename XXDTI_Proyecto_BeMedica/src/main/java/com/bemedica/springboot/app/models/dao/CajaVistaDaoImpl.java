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
	public List<Object []> findAll(int num1) {
		return em.createNativeQuery("call CorteCierre("+num1+")").getResultList();
	}
	
	@Transactional(readOnly =true)
	@SuppressWarnings("unchecked")
	
	@Override
	public List<Object[]> findAll2(int num1) {
		return em.createNativeQuery("call ReporteCorteCierre("+num1+")").getResultList();
	}
	
	@Transactional(readOnly =true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAll3(int num1) {
		return em.createNativeQuery("call ultimo_corte("+num1+")").getResultList();
	}

	@Transactional(readOnly =true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAll4(int num1) {
		return em.createNativeQuery("call Fechas("+num1+")").getResultList();
	}

	@Transactional(readOnly =true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAll5(int num1) {
	
		return em.createNativeQuery("call TotalEfecTarje("+num1+")").getResultList();	
	}	
	
	@Transactional(readOnly =true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAll7() {
		return em.createNativeQuery("call montos_corte").getResultList();	
	}

}