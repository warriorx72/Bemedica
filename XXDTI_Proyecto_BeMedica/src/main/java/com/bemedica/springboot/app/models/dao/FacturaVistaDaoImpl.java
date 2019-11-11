package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Factura;
import com.bemedica.springboot.app.models.entity.FacturaVista;

@Repository
public class FacturaVistaDaoImpl implements IFacturaVistaDao {

	@PersistenceContext 
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<FacturaVista> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from FacturaVista").getResultList();
	}
	
	@Override
	@Transactional(readOnly=true)
	public Factura findOne(Long FacturaId) {
		
		return em.find(Factura.class, FacturaId);
	}
	
	@Override
	@Transactional
	public void delete(Long FacturaId) {
		Factura factura = findOne(FacturaId);	
		em.remove(factura);
	}

	@Override
	@Transactional
	public void save(Factura FacturaId) {
		
		
	}

}
