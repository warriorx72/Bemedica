package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.Paciente;

@Repository
public class PacienteDaoImpl implements IPacienteDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Paciente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Paciente").getResultList();
	}
	 @Transactional
	@Override
	public Paciente findOne(Long id) {
		return em.find(Paciente.class, id);
	}
	 @Transactional
	@Override
	public void save(Paciente paciente) {
		if(paciente.getPaciente_id() != null && paciente.getPaciente_id() >0) {
			em.merge(paciente);
		} else {
			em.persist(paciente);
		}
	}

	 @Transactional
	@Override
	public void delete(Long id) {
		em.remove(findOne(id));
	}

}
