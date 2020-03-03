package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Medicos;
import com.bemedica.springboot.app.models.entity.MedicosVista;

@Repository
public class MedicosDaoImpl implements IMedicosDao {

	@PersistenceContext 
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Medicos> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery("from Medicos").getResultList();
	}
	
	@Override
	@Transactional
	public void save(Medicos medicos) {
		if(medicos.getPersonaId() > 0) {
			em.merge(medicos);
		}else {
			em.persist(medicos);
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public Medicos findOne(Long MedicoId) {
		
		return em.find(Medicos.class, MedicoId);
	}

	@Override
	@Transactional
	public void delete(Long MedicoId) {
		Medicos medico= findOne(MedicoId);	
		em.remove(medico);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<MedicosVista> mv() {
		// TODO Auto-generated method stub
		return em.createQuery("from MedicosVista").getResultList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Object findByPersona(Long PersonaId) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("select medico_id from medicos where persona_id = "+PersonaId+" group by persona_id").getSingleResult(); 
	}
}
