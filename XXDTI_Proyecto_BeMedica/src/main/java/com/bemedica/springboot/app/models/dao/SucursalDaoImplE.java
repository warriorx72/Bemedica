package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import com.bemedica.springboot.app.models.entity.Sucursal;
import com.bemedica.springboot.app.models.entity.SucursalE;
@Repository("SucursalDaoJPA")
public class SucursalDaoImplE implements ISucursal {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings ("unchecked")

	@Override
	@Transactional(readOnly= true)
	public List<SucursalE> findAll() {
		// TODO Auto-generated method stub
		
		
		return em.createQuery("from Sucursal").getResultList();
	
		    
		
	}

	@Override
	@Transactional
	public void save(SucursalE sucursal) {
		if(sucursal.getSucursalId()  !=null &&  sucursal.getSucursalId()>0) {
			em.merge(sucursal);

		}
		else {
			em.persist(sucursal);
		}
	}


	@Override
	@Transactional(readOnly= true)
	public SucursalE findOne(Long id) 
	{
		
		return em.find(SucursalE.class,id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
	
		em.remove(findOne(id));
		
		
	}

}
