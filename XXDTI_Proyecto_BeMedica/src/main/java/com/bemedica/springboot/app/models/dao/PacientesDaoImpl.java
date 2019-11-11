package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Pacientes;
import com.bemedica.springboot.app.models.entity.PacienteVista;

@Repository
public class PacientesDaoImpl implements IPacientesDao {

	@PersistenceContext 
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Pacientes> findAll() {

		return em.createQuery("from Pacientes").getResultList();
	}

	@Override
	public void save(Pacientes pacientes) {

		
	}

	@Override
	@Transactional(readOnly=true)
	public Pacientes findOne(Long PacienteId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Long PacienteId) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<PacienteVista> pv() {

		return em.createQuery("from PacienteVista").getResultList();
	}

}
