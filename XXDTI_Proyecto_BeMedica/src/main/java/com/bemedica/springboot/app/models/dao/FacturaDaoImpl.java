package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Factura;

@Repository
public class FacturaDaoImpl implements IFacturaDao {

	@PersistenceContext 
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Factura> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Factura").getResultList();
		}

	@Override
	@Transactional
	public void save(Factura Factura) {
		if(Factura.getFacturaId() > 0) {
			em.merge(Factura);
		}else {
			em.persist(Factura);
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public Factura findOne(Long FacturaId) {
		// TODO Auto-generated method stub
		return em.find(Factura.class, FacturaId);
		}

	@Override
	@Transactional
	public void delete(Long FacturaId) {
		Factura factura = findOne(FacturaId);	
		em.remove(factura);
		
	}
	
}