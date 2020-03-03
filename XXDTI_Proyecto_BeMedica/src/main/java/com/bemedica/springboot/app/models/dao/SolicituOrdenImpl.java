package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bemedica.springboot.app.models.entity.SolicitudOrden;
import com.bemedica.springboot.app.repository.SolicitudOrdenRepository;

@Service
public class SolicituOrdenImpl implements ISolicitudOrdenDao {

	@PersistenceContext
	private EntityManager em;
	@Autowired
	private SolicitudOrdenRepository repository;

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
	}

	@Override
	public void save(SolicitudOrden solicitud) {
		// TODO Auto-generated method stub
		repository.save(solicitud);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<SolicitudOrden> findAllSol() {
		// TODO Auto-generated method stub
	return em.createNativeQuery("call solicitud_orden()").getResultList();
	
}

	@Override
	public SolicitudOrden findOne(Long solicitud) {
		// TODO Auto-generated method stub
		return repository.findById(solicitud).orElse(null);
	}

}
