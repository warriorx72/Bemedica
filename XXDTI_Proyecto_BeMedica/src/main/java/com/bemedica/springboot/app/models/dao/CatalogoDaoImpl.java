package com.bemedica.springboot.app.models.dao;


import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Catalogo;
@Repository
public class CatalogoDaoImpl implements ICatalogoDao {
	
	@PersistenceContext
	private EntityManager em;
	@Transactional(readOnly =true)
	@SuppressWarnings("unchecked")
	@Override
	public List<Catalogo> findAll() {
		
		// TODO Auto-generated method stub
		return em.createQuery("From Catalogo").getResultList();
	}
	
	//-----------------------------
		@Override
		@Transactional(readOnly =true)
		public Catalogo findOne(Long CatalogoId) {
			// TODO Auto-generated method stub
			return em.find(Catalogo.class, CatalogoId);
		}
	    
	
	//-----------------------------MetodoSave
	
	@Override
	@Transactional
	public void save(Catalogo catalogo) {
		if(catalogo.getCatalogoId() != null && catalogo.getCatalogoId() >0) {
			em.merge(catalogo);
		}else {
			em.persist(catalogo);	
		}
	}
	
	
	//-----------------------------metodoDelete
	@Override
	@Transactional
	public void delete(Long CatalogoId) {
		em.remove(findOne(CatalogoId));
		
	}

	
	

}
