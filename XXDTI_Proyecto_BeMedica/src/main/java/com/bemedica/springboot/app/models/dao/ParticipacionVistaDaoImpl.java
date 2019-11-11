package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.ParticipacionVista;

@Repository
public class ParticipacionVistaDaoImpl implements IParticipacionVistaDao {

	@PersistenceContext 
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<ParticipacionVista> findAll() {
		
		return em.createQuery("from ParticipacionVista").getResultList();
	}

	@Override
	public List<ParticipacionVista> findOneById(Long Id) {
		// TODO Auto-generated method stub
		return null;
	}


}
