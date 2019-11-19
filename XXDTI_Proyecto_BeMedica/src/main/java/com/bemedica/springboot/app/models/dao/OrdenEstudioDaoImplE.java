package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.OrdenEstudio;
import com.bemedica.springboot.app.models.entity.OrdenEstudioE;
@Repository
public class OrdenEstudioDaoImplE implements IOrdenEstudioDaoE{

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<OrdenEstudioE> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from OrdenEstudioE").getResultList();
	}
	 @Transactional
	@Override
	public OrdenEstudioE findOne(Long id) {
		return em.find(OrdenEstudioE.class, id);
	}
	 @Transactional
	@Override
	public void save(OrdenEstudioE ordenestudio) {
		if(ordenestudio.getOrdenEstudioId() != null && ordenestudio.getOrdenEstudioId()>0) {
			em.merge(ordenestudio);
		} else {
			em.persist(ordenestudio);
		}
	}

	 @Transactional
	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
