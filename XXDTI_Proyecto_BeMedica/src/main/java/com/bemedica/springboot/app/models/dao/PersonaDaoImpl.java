package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.Persona;

@Repository
public  class PersonaDaoImpl implements IPersonaDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Persona> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Persona").getResultList();
	}
	 @Transactional
	@Override
	public Persona findOne(Long id) {
		return em.find(Persona.class, id);
	}
	 @Transactional
	@Override
	public void save(Persona persona) {
		if(persona.getPersona_id() != null && persona.getPersona_id() >0) {
			em.merge(persona);
		} else {
			em.persist(persona);
		}
	}

	 @Transactional
	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
