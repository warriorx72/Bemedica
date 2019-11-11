package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.Medico;

@Repository
public class MedicoDaoImpl implements IMedicoDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Medico> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Medico").getResultList();
	}
	 @Transactional
	@Override
	public Medico findOne(Long id) {
		return em.find(Medico.class, id);
	}
	 @Transactional
	@Override
	public void save(Medico medico) {
		if(medico.getMedico_id() != null && medico.getMedico_id() >0) {
			em.merge(medico);
		} else {
			em.persist(medico);
		}
	}

	 @Transactional
	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
