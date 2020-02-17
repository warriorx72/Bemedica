package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.CajaChica;
@Repository
public class CajaChicaDaoImpl implements ICajaChicaDao {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly =true)
	public List<CajaChica> findAll() {
		// TODO Auto-generated method stub
		return  em.createQuery("From CajaChica").getResultList();
	}

	@Override
	@Transactional
	public void save(CajaChica cajaChica) {
		// TODO Auto-generated method stub
		if(cajaChica.getCajaChicaId() != null && cajaChica.getCajaChicaId() >0) {
			em.merge(cajaChica);
		}else {
			em.persist(cajaChica);	
		}
	}

	@Override
	@Transactional
	public CajaChica findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(CajaChica.class, id);
	}
	@Transactional
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		em.remove(findOne(id));
	}


}
