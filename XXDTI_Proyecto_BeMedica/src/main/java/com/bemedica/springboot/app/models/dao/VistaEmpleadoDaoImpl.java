package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.VistaEmpleado;

@Repository
public  class VistaEmpleadoDaoImpl implements IVistaEmpleadoDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<VistaEmpleado> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from VistaEmpleado").getResultList();
	}
	

	

}
