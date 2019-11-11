package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.MedicoPago;

@Repository
public class MedicoPagoDaoImpl implements IMedicoPagoDao {

	@PersistenceContext 
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<MedicoPago> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from MedicoPago").getResultList();
	}

	@Override
	@Transactional
	public void save(MedicoPago medicos) {
		if(medicos.getMedicoId() != null && medicos.getMedicoId() > 0) {
			em.merge(medicos);
		}else {
			em.persist(medicos);
		}
		
	}

	@Override
	@Transactional
	public void delete(Long mpId) {
		MedicoPago mp = findOne(mpId);	
		em.remove(mp);
	}

	@Override
	@Transactional(readOnly=true)
	public MedicoPago findOne(Long medicoId) {
		
		return em.find(MedicoPago.class, medicoId);
	}
}
