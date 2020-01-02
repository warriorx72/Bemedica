package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.ConvenioEstudio;
import com.bemedica.springboot.app.models.entity.ConvenioEstudioVista;


@Repository("ConvenioEstudioDaoJPA")
public class ConvenioEstudioDaoImpl implements IConvenioEstudio {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<ConvenioEstudio> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from Direccion").getResultList();
		
	}


	@Override
	@Transactional
	public void save(ConvenioEstudio convenioestudio) {
		if(convenioestudio.getCeId() !=null &&  convenioestudio.getCeId()>0) {
			em.merge(convenioestudio);
			
		}
		else {
			em.persist(convenioestudio);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public ConvenioEstudio findOne(Long id) 
	{
		
		return em.find(ConvenioEstudio.class,id);
	}


	@Override
	@Transactional
	public void delete(Long id) {
	
		em.remove(findOne(id));
		
		
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<ConvenioEstudioVista> cev(Long id ) {
		// TODO Auto-generated method stub
		return em.createQuery ("from ConvenioEstudioVista where convenio_id="+id).getResultList();
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findExa(String id) {
		// TODO Auto-generated method stub
		
		return em.createNativeQuery("SELECT\r\n" + 
				"	coes.estudio_id\r\n" + 
				"FROM\r\n" + 
				"	convenio co\r\n" + 
				"	INNER JOIN convenio_estudio coes ON co.convenio_id = coes.convenio_id\r\n" + 
				"WHERE 1=1 AND co.convenio_id="+id+" AND coes.estudio_tipo='examen'").getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findCul(String id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("SELECT\r\n" + 
				"	coes.estudio_id\r\n" + 
				"FROM\r\n" + 
				"	convenio co\r\n" + 
				"	INNER JOIN convenio_estudio coes ON co.convenio_id = coes.convenio_id\r\n" + 
				"WHERE 1=1 AND co.convenio_id="+id+" AND coes.estudio_tipo='cultivo'").getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findGab(String id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("SELECT\r\n" + 
				"	coes.estudio_id\r\n" + 
				"FROM\r\n" + 
				"	convenio co\r\n" + 
				"	INNER JOIN convenio_estudio coes ON co.convenio_id = coes.convenio_id\r\n" + 
				"WHERE 1=1 AND co.convenio_id="+id+" AND coes.estudio_tipo='gabinete'").getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findPaq(String id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("SELECT\r\n" + 
				"	copa.paquete_id \r\n" + 
				"FROM\r\n" + 
				"	convenio co\r\n" + 
				"	INNER JOIN convenio_paquete copa ON co.convenio_id = copa.convenio_id \r\n" + 
				"WHERE\r\n" + 
				"	co.convenio_id ="+id).getResultList();
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findPer(String id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("SELECT\r\n" + 
				"	cope.perfil_id \r\n" + 
				"FROM\r\n" + 
				"	convenio co\r\n" + 
				"	INNER JOIN convenio_perfil cope ON co.convenio_id = cope.convenio_id \r\n" + 
				"WHERE\r\n" + 
				"	co.convenio_id ="+id).getResultList();
	}

	
	

}
