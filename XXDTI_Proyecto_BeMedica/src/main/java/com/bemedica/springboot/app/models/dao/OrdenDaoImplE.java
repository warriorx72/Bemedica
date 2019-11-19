package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import org.springframework.stereotype.Repository;
import com.bemedica.springboot.app.models.entity.Orden;
import com.bemedica.springboot.app.models.entity.OrdenE;


@Repository("OrdenDaoJPA")
public class OrdenDaoImplE implements IOrdenDaoE{

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<OrdenE> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from OrdenE").getResultList();
	}
	 @Transactional
	@Override
	public OrdenE findOne(Long id) {
		return em.find(OrdenE.class, id);
	}
	 @Transactional
	@Override
	public void save(OrdenE orden) {
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