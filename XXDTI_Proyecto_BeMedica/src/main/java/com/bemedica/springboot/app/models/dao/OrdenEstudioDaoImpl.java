package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.OrdenEstudio;

@Repository
public class OrdenEstudioDaoImpl implements IOrdenEstudioDao{

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<OrdenEstudio> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from OrdenEstudio").getResultList();
	}
	 @Transactional
	@Override
	public OrdenEstudio findOne(Long id) {
		return em.find(OrdenEstudio.class, id);
	}
	 @Transactional
	@Override
	public void save(OrdenEstudio ordenestudio) {
		if(ordenestudio.getOrden_id() != null && ordenestudio.getOrden_id() >0) {
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
