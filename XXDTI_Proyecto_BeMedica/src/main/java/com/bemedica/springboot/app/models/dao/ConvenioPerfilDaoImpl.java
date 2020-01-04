package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.ConvenioPerfil;

@Repository("ConvenioPerfilDaoJPA")
public class ConvenioPerfilDaoImpl implements IConvenioPerfilDao {

	@PersistenceContext
	private EntityManager em;
	@SuppressWarnings ("unchecked")
	@Override
	@Transactional(readOnly= true)
	public List<ConvenioPerfil> findAll() {
		return em.createQuery("from ConvenioPerfil").getResultList();
	}

	@Override
	@Transactional
	public void save(ConvenioPerfil cpe) {
		if(cpe.getCpeId() !=null &&  cpe.getCpeId()>0) {
			em.merge(cpe);

		}
		else {
			em.persist(cpe);
		}
	}


	@Override
	@Transactional(readOnly= true)
	public ConvenioPerfil findOne(Long id) 
	{
		
		return em.find(ConvenioPerfil.class,id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
	
		em.remove(findOne(id));
		
		
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Object[]> findPerfil( Long id) {
		// TODO Auto-generated method stub
		
		
		
		
		List<Object[]> re= em.createNativeQuery("SELECT perfiles.perfil_nombre, perfiles.perfil_id\r\n" + 
				"	FROM perfiles   \r\n" + 
				"	WHERE \r\n" + 
				"	1=1 and perfiles.perfil_estatus=1\r\n" + 
				"	and perfiles.perfil_id NOT IN \r\n" + 
				"	(SELECT convenio_perfil.perfil_id \r\n" + 
				"	 FROM  perfiles , convenio_perfil \r\n" + 
				"			WHERE 1=1 \r\n" + 
				"			and convenio_perfil.perfil_id=perfiles.perfil_id \r\n" + 
				"			and convenio_perfil.convenio_id="+id+")").getResultList();
		
		
		
		return  re;
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Object[]> findPerfilConvenio(Long id) {
		// TODO Auto-generated method stub
		
		
		
		
		List<Object[]> re= em.createNativeQuery("SELECT perfiles.perfil_nombre,convenio_perfil.cpe_id\r\n" + 
				"	 FROM  perfiles , convenio_perfil \r\n" + 
				"			WHERE 1=1 \r\n" + 
				"			and convenio_perfil.perfil_id=perfiles.perfil_id \r\n" + 
				"			and convenio_perfil.convenio_id="+id).getResultList();
		
		
		
		return  re;
	}


}
