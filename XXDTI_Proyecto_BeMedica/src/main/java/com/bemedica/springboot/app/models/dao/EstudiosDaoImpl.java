package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Estudios;
import com.bemedica.springboot.app.models.entity.EstudiosView;
@Repository
public class EstudiosDaoImpl implements IEstudiosDao {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<Estudios> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from Estudios").getResultList();
	}

	@Override
	@Transactional
	public void save(Estudios estudios) {
		// TODO Auto-generated method stub
		if(estudios.getEstudioId() !=null &&  estudios.getEstudioId()>0) {
			em.merge(estudios);
			
		}
		else {
			em.persist(estudios);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public Estudios findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(Estudios.class,id);
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
		String auxs = em.createQuery ("SELECT MAX(EstudioId) FROM Estudios").getSingleResult().toString();
		int aux = Integer.parseInt(auxs);	
		return aux;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<EstudiosView> findAllEstudios() {
		// TODO Auto-generated method stub
		return em.createNativeQuery("call estudios_view()",EstudiosView.class).getResultList(); 
	}



}
