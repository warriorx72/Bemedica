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

}
