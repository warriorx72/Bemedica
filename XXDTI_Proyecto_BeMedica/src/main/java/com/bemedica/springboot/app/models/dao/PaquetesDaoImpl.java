package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Paquetes;
@Repository
public class PaquetesDaoImpl implements IPaquetesDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Paquetes> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from Paquetes").getResultList();
	}

	@Override
	@Transactional
	public void save(Paquetes paquetes) {
		// TODO Auto-generated method stub
		if(paquetes.getPaqueteId() !=null &&  paquetes.getPaqueteId()>0) {
			em.merge(paquetes);
			
		}
		else {
			em.persist(paquetes);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public Paquetes findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(Paquetes.class,id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		em.remove(findOne(id));
	}

	@Override
	@Transactional
	public int findLastId() {
		// TODO Auto-generated method stub
		String auxs = em.createQuery ("SELECT MAX(PaqueteId) FROM Paquetes").getSingleResult().toString();
		int aux = Integer.parseInt(auxs);	
		return aux;
	}

}
