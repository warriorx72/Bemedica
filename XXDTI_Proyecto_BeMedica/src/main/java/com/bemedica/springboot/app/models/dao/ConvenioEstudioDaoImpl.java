package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.ConvenioEstudio;
import com.bemedica.springboot.app.models.entity.ConvenioEstudioVista;


@Repository("ConvenioEstudioDaoJPA")
public class ConvenioEstudioDaoImpl implements IConvenioEstudio {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<ConvenioEstudio> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from Direccion").getResultList();
		
	}


	@Override
	@Transactional
	public void save(ConvenioEstudio convenioestudio) {
		if(convenioestudio.getCeId() !=null &&  convenioestudio.getCeId()>0) {
			em.merge(convenioestudio);
			
		}
		else {
			em.persist(convenioestudio);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public ConvenioEstudio findOne(Long id) 
	{
		
		return em.find(ConvenioEstudio.class,id);
	}


	@Override
	@Transactional
	public void delete(Long id) {
	
		em.remove(findOne(id));
		
		
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<ConvenioEstudioVista> cev(Long id ) {
		// TODO Auto-generated method stub
		return em.createQuery ("from ConvenioEstudioVista where convenio_id="+id).getResultList();
		
	}

	
	

}
