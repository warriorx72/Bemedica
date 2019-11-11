package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.VistaOrdenEstudio;

@Repository
public  class VistaOrdenEstudioDaoImpl implements IVistaOrdenEstudioDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<VistaOrdenEstudio> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from VistaOrdenEstudio").getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override

	public List<VistaOrdenEstudio> voe(Long orden_id) {
		// TODO Auto-generated method stub
		
	
		return em.createQuery("from VistaOrdenEstudio where orden_id="+orden_id).getResultList();
	}
	

	

}
