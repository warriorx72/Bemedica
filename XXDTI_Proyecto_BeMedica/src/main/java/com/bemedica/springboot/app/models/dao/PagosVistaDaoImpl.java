package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.PagosVista;

@Repository
public class PagosVistaDaoImpl implements IPagosVistaDao {

	@PersistenceContext 
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<PagosVista> findAll() {
		
		return em.createQuery("from PagosVista").getResultList();
	}
	
	@Override
	@Transactional(readOnly=true)
	public PagosVista findOne(Long Id) {
		
		return em.find(PagosVista.class, Id);
	}
	
	@Override
	@Transactional
	public void delete(Long Id) {
		PagosVista pagos= findOne(Id);	
		em.remove(pagos);
	}
}
