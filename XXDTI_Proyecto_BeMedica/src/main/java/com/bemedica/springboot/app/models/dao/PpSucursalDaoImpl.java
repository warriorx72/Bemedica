package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.PpSucursal;
import com.bemedica.springboot.app.models.entity.PpSucursalVista;


@Repository("PpSucursalDaoJPA")
public class PpSucursalDaoImpl implements IPpSucursal {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<PpSucursal> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from PpSucursal").getResultList();
		
	}

	@Override
	@Transactional
	public void save(PpSucursal pps) {
		if(pps.getPpsId()  !=null &&  pps.getPpsId()>0) {
			em.merge(pps);

		}
		else {
			em.persist(pps);
		}
	}


	@Override
	@Transactional(readOnly= true)
	public PpSucursal findOne(Long id) 
	{
		
		return em.find(PpSucursal.class,id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
	
		em.remove(findOne(id));
		
		
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<PpSucursalVista> ppsv(Long id) {
		// TODO Auto-generated method stub
		return em.createQuery ("from PpSucursalVista where paque_perfil_id="+id).getResultList();
	}
	
	
}
