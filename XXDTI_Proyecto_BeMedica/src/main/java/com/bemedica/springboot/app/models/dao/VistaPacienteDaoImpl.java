package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.VistaPaciente;

@Repository
public class VistaPacienteDaoImpl implements IVistaPacienteDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<VistaPaciente> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from VistaPaciente").getResultList();
	}

}
