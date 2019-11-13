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
	
}