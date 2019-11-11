package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bemedica.springboot.app.models.entity.CajaVista;


@Repository
public class CajaVistaDaoImpl implements ICajaVistaDao {
	
	@PersistenceContext
	private EntityManager em;
	@Transactional(readOnly =true)
	@SuppressWarnings("unchecked")
	@Override
	
	public List<CajaVista> findAll() {
	return em.createQuery("From CajaVista").getResultList();
	}
}