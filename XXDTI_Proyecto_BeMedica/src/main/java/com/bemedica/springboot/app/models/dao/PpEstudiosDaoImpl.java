package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.PpEstudioVista;
import com.bemedica.springboot.app.models.entity.PpEstudios;


@Repository("PpEstudiosDaoJPA")
public class PpEstudiosDaoImpl implements IPpEstudios {
	
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<PpEstudios> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from PpEstudios").getResultList();
		
	}

	@Override
	@Transactional
	public void save(PpEstudios ppe) {
		if(ppe.getPpId()  !=null &&  ppe.getPpId()>0) {
			em.merge(ppe);

		}
		else {
			em.persist(ppe);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public PpEstudios findOne(Long id) 
	{
		
		return em.find(PpEstudios.class,id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
	
		em.remove(findOne(id));
		
		
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<PpEstudioVista> ppev(Long id) {
		// TODO Auto-generated method stub
		return em.createQuery ("from PpEstudioVista where paque_perfil_id="+id).getResultList();
	}

	
	

}
