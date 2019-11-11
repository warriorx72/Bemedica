package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.Direccion;

@Repository
public  class DireccionDaoImpl implements IDireccionDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Direccion> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Direccion").getResultList();
	}
	 @Transactional
	@Override
	public Direccion findOne(Long id) {
		return em.find(Direccion.class, id);
	}
	 @Transactional
	@Override
	public void save(Direccion direccion) {
		if(direccion.getDireccion_id() != null && direccion.getDireccion_id() >0) {
			em.merge(direccion);
		} else {
			em.persist(direccion);
		}
	}

	 @Transactional
	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
