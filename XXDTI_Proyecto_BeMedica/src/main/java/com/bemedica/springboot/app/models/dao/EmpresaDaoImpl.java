package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Empresa;

@Repository("EmpresaDaoJPA")
public class EmpresaDaoImpl implements IEmpresa{
	
	@PersistenceContext
	private EntityManager em;
	
	
	
	
	
	
	
	
	
	
	
	
	@SuppressWarnings ("unchecked")
	@Override
	@Transactional(readOnly= true)
	public List<Empresa> findAll() {
		return em.createQuery("from Empresa").getResultList();
	}

	@Override
	@Transactional
	public void save(Empresa empresa) {
		if(empresa.getEmpresaId()  !=null &&  empresa.getEmpresaId()>0) {
			em.merge(empresa);

		}
		else {
			em.persist(empresa);
		}
	}


	@Override
	@Transactional(readOnly= true)
	public Empresa findOne(Long id) 
	{
		
		return em.find(Empresa.class,id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
	
		em.remove(findOne(id));
		
		
	}
	
	
	
	
	@Transactional
	@Override	
	public Integer Id() {
		
		Integer val = (Integer) em.createNativeQuery("SELECT MAX(empresa_id) FROM empresa;").getSingleResult();
		return val;
	}
	
	
	
	
	

}
