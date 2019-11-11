package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.EstudiosListQ;
import com.bemedica.springboot.app.models.entity.PerfilesEstudios;
import com.bemedica.springboot.app.models.entity.PerfilesEstudiosView;
@Repository
public class PerfilesEstudiosDaoImpl implements IPerfilesEstudiosDao{

	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<PerfilesEstudios> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from PerfilesEstudios").getResultList();
	}

	@Override
	@Transactional
	public void save(PerfilesEstudios perfilesEstudios) {
		// TODO Auto-generated method stub
		if(perfilesEstudios.getPerfilesEstudiosId() !=null &&  perfilesEstudios.getPerfilesEstudiosId()>0) {
			em.merge(perfilesEstudios);
			
		}
		else {
			em.persist(perfilesEstudios);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public PerfilesEstudios findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(PerfilesEstudios.class,id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		em.remove(findOne(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<PerfilesEstudiosView> findAllById(Long id) {
		// TODO Auto-generated method stub
		return em.createQuery ("from PerfilesEstudiosView where PerfilId="+id).getResultList();
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
				"		perfiles_estudios pe,\r\n" + 
				"		estudios e \r\n" + 
				"	WHERE\r\n" + 
				"		1 = 1 \r\n" + 
				"		AND pe.estudio_id = e.estudio_id \r\n" + 
				"		AND pe.perfil_id ="+id+ 
				"	) \r\n" + 
				"	AND e.estudio_id NOT IN (\r\n" + 
				"	SELECT\r\n" + 
				"		pc.cultivo_id \r\n" + 
				"	FROM\r\n" + 
				"		perfiles_cultivos pc,\r\n" + 
				"		estudios e \r\n" + 
				"	WHERE\r\n" + 
				"		1 = 1 \r\n" + 
				"		AND pc.cultivo_id = e.estudio_id \r\n" + 
				"	AND pc.perfil_id ="+id+ 
				"	)",EstudiosListQ.class).getResultList();
	}
}
