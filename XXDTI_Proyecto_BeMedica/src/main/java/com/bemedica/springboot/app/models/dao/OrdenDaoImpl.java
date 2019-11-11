package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.Orden;

@Repository
public class OrdenDaoImpl implements IOrdenDao{

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Orden> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Orden").getResultList();
	}
	 @Transactional
	@Override
	public Orden findOne(Long id) {
		return em.find(Orden.class, id);
	}
	 @Transactional
	@Override
	public void save(Orden orden) {
		if(orden.getOrden_id() != null && orden.getOrden_id() >0) {
			em.merge(orden);
		} else {
			em.persist(orden);
		}
	}

	 @Transactional
	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
