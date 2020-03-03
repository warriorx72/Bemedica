package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bemedica.springboot.app.models.entity.SolicitudDetalles;
import com.bemedica.springboot.app.repository.SolicitudDetallesRepository;

@Service
public class SolicitudDetallesDaoImpl implements ISolicitudDetallesDao {
	@Autowired
	private EntityManager em;
	@Autowired
	private SolicitudDetallesRepository repository;

	@SuppressWarnings("unchecked")
	@Override
	public List<Object[]> findAllById(Long Sid) {
		// TODO Auto-generated method stub
		return em
				.createNativeQuery("SELECT estudio_id, MAX(o.orden_id), tipo FROM app_solicitud_detalle app\r\n"
						+ "INNER JOIN orden o\r\n" + "WHERE solicitud_id = " + Sid + "\r\n" + "GROUP BY solicitud_detalle_id")
				.getResultList();
	}

	@Override
	public SolicitudDetalles findOne(Long detallesId) {
		// TODO Auto-generated method stub
		return repository.findById(detallesId).orElse(null);
	}

	@Override
	public void save(SolicitudDetalles detalles) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
