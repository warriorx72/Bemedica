package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Perfiles;
@Repository
public class PerfilesDaoImpl implements IPerfilesDao {
	
	
	@PersistenceContext
	private EntityManager em;
	
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Perfiles> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from Perfiles").getResultList();
	}

	@Override
	@Transactional
	public void save(Perfiles perfiles) {
		// TODO Auto-generated method stub
		if(perfiles.getPerfilId() !=null &&  perfiles.getPerfilId()>0) {
			em.merge(perfiles);
			
		}
		else {
			em.persist(perfiles);
		}
	}

	@Override
	@Transactional
	public Perfiles findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(Perfiles.class,id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		em.remove(findOne(id));
	}

}
