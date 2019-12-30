package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Estudio;
import com.bemedica.springboot.app.models.entity.EstudioE;


@Repository("EstudioDaoJPA")
public class EstudioDaoImplE implements IEstudio {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<EstudioE> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from EstudioE").getResultList();
		
	}

	@Override
	@Transactional
	public void save(EstudioE estudio) {
		if(estudio.getEstudioId()!=null &&  estudio.getEstudioId()>0) {
			em.merge(estudio);
			
		}
		else {
			em.persist(estudio);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public EstudioE findOne(Long id) 
	{
		
		return em.find(EstudioE.class,id);
	}


	@Override
	@Transactional
	public void delete(Long id) {
	
		em.remove(findOne(id));
		
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Object[]> select( Long id) {
		// TODO Auto-generated method stub
		
		
		
		
		List<Object[]> re= em.createNativeQuery("SELECT estudios.estudio_nombre, estudios.estudio_id,estudios.estudio_tipo\r\n" + 
				"FROM estudios   \r\n" + 
				"WHERE \r\n" + 
				"1=1 and estudios.estudio_estatus=1\r\n" + 
				"and estudios.estudio_id NOT IN \r\n" + 
				"(SELECT convenio_estudio.estudio_id \r\n" + 
				" FROM  estudios , convenio_estudio \r\n" + 
				"		WHERE 1=1 \r\n" + 
				"		and convenio_estudio.estudio_id=estudios.estudio_id \r\n" + 
				"		and convenio_estudio.convenio_id="+id+")").getResultList();
		
		
		
		return  re;
	}

}
