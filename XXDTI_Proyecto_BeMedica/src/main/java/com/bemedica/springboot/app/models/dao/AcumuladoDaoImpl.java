package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Acumulado;
import com.bemedica.springboot.app.models.entity.MedicoPago;

@Repository
public class AcumuladoDaoImpl implements IAcumuladoDao {

	@PersistenceContext 
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Acumulado> findAll() {
		
		return em.createQuery("from Acumulado").getResultList();
	}
	
	@Override
	@Transactional(readOnly=true)
	public MedicoPago findOne(int Id) {
		
		return em.find(MedicoPago.class, Id);
	}
	
	@Override
	@Transactional
	public void delete(int Id) {
		MedicoPago mp= findOne(Id);	
		em.remove(mp);
	}

	@Override
	@Transactional
	public void save(MedicoPago MedicoId) {
		if(MedicoId.getMedicoId() != null && MedicoId.getMedicoId() > 0) {
			em.merge(MedicoId);
		}else {
			em.persist(MedicoId);
		}
		
	}
}
