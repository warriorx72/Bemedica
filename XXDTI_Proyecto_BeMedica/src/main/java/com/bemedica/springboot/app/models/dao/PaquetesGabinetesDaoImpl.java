package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.dao.IPaquetesGabinetesDao;
import com.bemedica.springboot.app.models.entity.PaquetesGabinetes;
@Repository
public class PaquetesGabinetesDaoImpl implements IPaquetesGabinetesDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<PaquetesGabinetes> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from PaquetesGabinetes").getResultList();
	}

	@Override
	@Transactional
	public void save(PaquetesGabinetes paquetesGabinetes) {
		// TODO Auto-generated method stub
		if(paquetesGabinetes.getPaGaId() !=null &&  paquetesGabinetes.getPaGaId()>0) {
			em.merge(paquetesGabinetes);
			
		}
		else {
			em.persist(paquetesGabinetes);
		}
	}

	@Override
	@Transactional
	public PaquetesGabinetes findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(PaquetesGabinetes.class,id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		em.remove(findOne(id));
	}

}
