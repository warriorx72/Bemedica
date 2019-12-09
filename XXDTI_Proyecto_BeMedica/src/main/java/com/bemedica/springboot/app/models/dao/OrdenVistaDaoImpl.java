package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.OrdenVista;
@Repository
public class OrdenVistaDaoImpl implements IOrdenVistaDao {
	@PersistenceContext
	private EntityManager em;
	@SuppressWarnings("unchecked")
	@Override
	public List<OrdenVista> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("From OrdenVista").getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> findById() {
		// TODO Auto-generated method stub
		return em.createQuery("Select e.EstudioNombre,o.orden_id From Estudios e,Orden o,OrdenEstudio oe where "
				+ "e.EstudioId=oe.estudio_id and o.orden_id=oe.orden_id").getResultList();
	}
}
