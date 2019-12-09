package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.Promociones;
@Repository
public class PromocionesDaoImpl implements IPromocionesDao{
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Promociones> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Promociones where PromocionEstatus =1").getResultList();
	}
	 @Transactional
	@Override
	public Promociones findOne(Long id) {
		return em.find(Promociones.class, id);
	}
	 @Transactional
	@Override
	public void save(Promociones promociones) {
		if(promociones.getPromocionId() != null && promociones.getPromocionId() >0) {
			em.merge(promociones);
		} else {
			em.persist(promociones);
		}
	}

	 @Transactional
	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
