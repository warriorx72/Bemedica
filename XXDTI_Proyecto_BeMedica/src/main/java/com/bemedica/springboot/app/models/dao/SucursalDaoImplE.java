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
		
		
		return em.createQuery("from SucursalE").getResultList();
	
		    
		
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
	

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Object[]> listarSucursales() {
		
		List<Object[]> re= em.createNativeQuery("select sucursal.sucursal_id, sucursal.sucursal_id_text ,sucursal.sucursal_nombre, CONCAT(direccion.direccion_municipio,' No.',direccion.direccion_numero_ext ) as direccion from sucursal, direccion \r\n" + 
				"	where 1=1\r\n" + 
				"	and sucursal.direccion_id=direccion.direccion_id\r\n" + 
				"	and sucursal.estatus=1").getResultList();
		return  re;

	}
}