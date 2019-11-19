package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Direccion;
import com.bemedica.springboot.app.models.entity.DireccionE;


@Repository("DireccionDaoJPA")
public class DireccionDaoImplE implements IDireccion {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<DireccionE> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from DireccionE").getResultList();
		
	}

	@Override
	@Transactional
	public void save(DireccionE direccion) {
		if(direccion.getDireccionId()!=null &&  direccion.getDireccionId()>0) {
			em.merge(direccion);
			
		}
		else {
			em.persist(direccion);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public DireccionE findOne(Long id) 
	{
		
		return em.find(DireccionE.class,id);
	}


	@Override
	@Transactional
	public void delete(Long id) {
	
		em.remove(findOne(id));
		
		
	}

}
