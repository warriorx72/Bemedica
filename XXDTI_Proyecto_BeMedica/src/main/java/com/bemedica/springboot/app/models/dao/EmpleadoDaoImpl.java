package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Empleado;
@Repository
public class EmpleadoDaoImpl implements IEmpleadoDao{
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings ("unchecked")

	@Override
	@Transactional(readOnly= true)
	public List<Empleado> findAll() {
		// TODO Auto-generated method stub
		
		
		return em.createQuery("from Empleado").getResultList();
	
		    
		
	}

	@Override
	@Transactional
	public void save(Empleado empleado) {
		if(empleado.getEmpleado_id()!=null &&  empleado.getEmpleado_id()>0) {
			em.merge(empleado);
			
		}
		else {
			em.persist(empleado);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public Empleado findOne(Long empleado_id) 
	{
		
		return em.find(Empleado.class,empleado_id);
	}

	@Override
	@Transactional
	public void delete(Long empleado_id) {
	
		em.remove(findOne(empleado_id));
		
		
	}

	
}
