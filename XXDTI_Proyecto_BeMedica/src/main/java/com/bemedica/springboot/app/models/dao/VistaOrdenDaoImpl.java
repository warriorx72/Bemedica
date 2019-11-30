package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.OrderBy;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.VistaOrden;
import com.bemedica.springboot.app.models.entity.VistaOrdenEstudio;

@Repository
public  class VistaOrdenDaoImpl implements IVistaOrdenDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	@OrderBy("orden_folio DESC")
	public List<VistaOrden> findAll2() {
		// TODO Auto-generated method stub
		return em.createQuery("from VistaOrden").getResultList();

	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	@OrderBy("orden_folio DESC")
	public List<VistaOrden> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from VistaOrden vo where orden_estatus in ('pendiente','finalizada') order by vo.orden_id desc").setMaxResults(1).getResultList();

	}

	@SuppressWarnings("unchecked")
	@Override

	public List<VistaOrden> vo(Long orden_id) {
		// TODO Auto-generated method stub
		
	
		return em.createNativeQuery("select orden_vista.monto from orden_vista where orden_vista.orden_id="+orden_id).getResultList();
	}
	

}
