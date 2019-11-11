package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Alertas;

@Repository
public class AlertasDaoImpl implements IAlertasDao {

	@PersistenceContext 
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Alertas> findAll() {
		return em.createQuery("from Alertas").getResultList();
	} 

	@Override
	@Transactional(readOnly=true)
	public Alertas findOne(Long AlertaId) {
		
		return em.find(Alertas.class, AlertaId);
	}

	@Override
	@Transactional
	public void save(Alertas alertas) {
		if(alertas.getAlertaId() != null && alertas.getAlertaId() > 0) {
			em.merge(alertas);
		}else {
		em.persist(alertas);
		}
	}

	@Override
	@Transactional
	public void delete(Long AlertaId) {
		Alertas a = findOne(AlertaId);
		em.remove(a);
	}

}
