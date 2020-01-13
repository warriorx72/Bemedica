package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.EstudiosListQ;
import com.bemedica.springboot.app.models.entity.PaquetesEstudios;
import com.bemedica.springboot.app.models.entity.PaquetesEstudiosQ;

@Repository
public class PaquetesEstudiosDaoImpl implements IPaquetesEstudiosDao{
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<PaquetesEstudios> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from PaquetesEstudios").getResultList();
	}

	@Override
	@Transactional
	public void save(PaquetesEstudios paquetesEstudios) {
		// TODO Auto-generated method stub
		if(paquetesEstudios.getPeId() !=null &&  paquetesEstudios.getPeId()>0) {
			em.merge(paquetesEstudios);
			
		}
		else {
			em.persist(paquetesEstudios);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public PaquetesEstudios findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(PaquetesEstudios.class,id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		em.remove(findOne(id));
	}

	@Override
	public List<Object> findAllById(Long id) {
		// TODO Auto-generated method stub
		Query query =em.createNativeQuery("SELECT\r\n" + 
				"pe.pe_id,\r\n" + 
				"pe.paquete_id,\r\n" + 
				"es.estudio_nombre\r\n" + 
				"FROM\r\n" + 
				"estudios AS es ,\r\n" + 
				"paquetes_estudios AS pe\r\n" + 
				"WHERE\r\n" + 
				"es.estudio_id = pe.estudio_id and pe.paquete_id="+id,PaquetesEstudiosQ.class);
		@SuppressWarnings("unchecked")
		List<Object> res= query.getResultList();

		return res;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<EstudiosListQ> findEstudios(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("SELECT\r\n" + 
				"	e.estudio_nombre,\r\n" + 
				"	e.estudio_id,\r\n" + 
				"	e.estudio_tipo \r\n" + 
				"FROM\r\n" + 
				"	estudios e \r\n" + 
				"WHERE\r\n" + 
				"	1 = 1 \r\n" + 
				"	AND e.estudio_id NOT IN (\r\n" + 
				"	SELECT\r\n" + 
				"		pe.estudio_id \r\n" + 
				"	FROM\r\n" + 
				"		paquetes_estudios pe,\r\n" + 
				"		estudios \r\n" + 
				"	WHERE\r\n" + 
				"		1 = 1 \r\n" + 
				"		AND pe.estudio_id = e.estudio_id \r\n" + 
				"		AND pe.paquete_id =" +id+ 
				"	) \r\n" + 
				"	AND e.estudio_id NOT IN (\r\n" + 
				"	SELECT\r\n" + 
				"		pc.cultivo_id \r\n" + 
				"	FROM\r\n" + 
				"		paquetes_cultivos pc,\r\n" + 
				"		estudios e \r\n" + 
				"	WHERE\r\n" + 
				"		1 = 1 \r\n" + 
				"		AND pc.cultivo_id = e.estudio_id \r\n" + 
				"		AND pc.paquete_id ="+id+ 
				"	) \r\n" + 
				"	AND e.estudio_id NOT IN (\r\n" + 
				"	SELECT\r\n" + 
				"		pg.gabinete_id \r\n" + 
				"	FROM\r\n" + 
				"		paquetes_gabinetes AS pg,\r\n" + 
				"		estudios AS e \r\n" + 
				"	WHERE\r\n" + 
				"		1 = 1 \r\n" + 
				"		AND e.estudio_id = pg.gabinete_id \r\n" + 
				"	AND pg.paquete_id =" +id+ 
				"	)",EstudiosListQ.class).getResultList();
	}

}