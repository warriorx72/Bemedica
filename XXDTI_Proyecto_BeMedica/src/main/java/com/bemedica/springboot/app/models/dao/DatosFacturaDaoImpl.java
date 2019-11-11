package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.DatosFactura;

@Repository
public class DatosFacturaDaoImpl implements IDatosFacturaDao {

	@PersistenceContext 
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<DatosFactura> findAll() {
		return em.createQuery("from DatosFactura").getResultList();
	}

	@Override
	@Transactional
	public void save(DatosFactura datosfactura) {
		if(datosfactura.getPacienteId() != null && datosfactura.getPacienteId() > 0) {
			em.merge(datosfactura);
		}else {
			em.persist(datosfactura);
		}
		
	}

	@Override
	@Transactional(readOnly=true)
	public DatosFactura findOne(Long PacienteId) {
		
		if (em.find(DatosFactura.class, PacienteId) == null)
		{
			return null;
		}
		else {
			return em.find(DatosFactura.class, PacienteId);
		}
		
		
	}

	@Override
	@Transactional
	public void delete(Long DatosFacturaId) {
		DatosFactura df = findOne(DatosFacturaId);
		em.remove(df);
	}

}
