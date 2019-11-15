package com.bemedica.springboot.app.models.dao;

import java.util.List;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.Estudio;

@Repository
public  class EstudioDaoImpl implements IEstudioDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Estudio> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Estudio").getResultList();
	}
	
	@Override
	@Transactional
	public List<Estudio> findBy() {
		// TODO Auto-generated method stub
		return em.createNativeQuery("select * from estudios where estudio_individual=1 and estudio_estatus=1").getResultList();
	}
	
	    
	
	 @Transactional
	@Override
	public Estudio findOne(Long id) {
		return em.find(Estudio.class, id);
	}
	 @Transactional
	@Override
	public void save(Estudio estudio) {
		if(estudio.getEstudio_id() != null && estudio.getEstudio_id() >0) {
			em.merge(estudio);
		} else {
			em.persist(estudio);
		}
	}

	 @Transactional
	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
