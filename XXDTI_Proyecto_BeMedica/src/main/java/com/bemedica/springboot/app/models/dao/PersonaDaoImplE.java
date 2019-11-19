package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.bemedica.springboot.app.models.entity.Persona;
import com.bemedica.springboot.app.models.entity.PersonaE;

@Repository("PersonaDaoJPA")
public class PersonaDaoImplE implements IPersona {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<PersonaE> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from Persona").getResultList();
		
	}

	@Override
	@Transactional
	public void save(PersonaE persona) {
		if(persona.getPersonaId()  !=null &&  persona.getPersonaId()>0) {
			em.merge(persona);

		}
		else {
			em.persist(persona);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public PersonaE findOne(Long id) 
	{
		
		return em.find(PersonaE.class,id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
	
		em.remove(findOne(id));
		
		
	}
	


}
