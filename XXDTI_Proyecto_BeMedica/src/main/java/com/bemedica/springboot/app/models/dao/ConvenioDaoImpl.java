package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Convenio;

import com.bemedica.springboot.app.models.entity.ConvenioVista;

@Repository("ConvenioDaoJPA")
public class ConvenioDaoImpl implements IConvenio{

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Convenio> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from Convenio").getResultList();
		
	}

	@Override
	@Transactional
	public void save(Convenio convenio) {
		if(convenio.getConvenioId() !=null &&  convenio.getConvenioId()>0) {
			em.merge(convenio);
			
		}
		else {
			em.persist(convenio);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public Convenio findOne(Long id) 
	{
		
		return em.find(Convenio.class,id);
	}


	@Override
	@Transactional
	public void delete(Long id) {
	
		em.remove(findOne(id));
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<ConvenioVista> All() {
		// TODO Auto-generated method stub
		return em.createQuery ("from ConvenioVista").getResultList();
		
	}

	

}
