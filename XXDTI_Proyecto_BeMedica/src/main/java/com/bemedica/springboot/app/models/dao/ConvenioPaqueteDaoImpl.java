package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.ConvenioPaquete;

@Repository("ConvenioPaqueteDaoJPA")
public class ConvenioPaqueteDaoImpl implements IConvenioPaqueteDao{
	
	@PersistenceContext
	private EntityManager em;
	@SuppressWarnings ("unchecked")
	@Override
	@Transactional(readOnly= true)
	public List<ConvenioPaquete> findAll() {
		return em.createQuery("from ConvenioPaquete").getResultList();
	}

	@Override
	@Transactional
	public void save(ConvenioPaquete copa) {
		if(copa.getCopaId() !=null &&  copa.getCopaId()>0) {
			em.merge(copa);

		}
		else {
			em.persist(copa);
		}
	}


	@Override
	@Transactional(readOnly= true)
	public ConvenioPaquete findOne(Long id) 
	{
		
		return em.find(ConvenioPaquete.class,id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
	
		em.remove(findOne(id));
		
		
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Object[]> findPaquete( Long id) {
		// TODO Auto-generated method stub
		
		
		
		
		List<Object[]> re= em.createNativeQuery("SELECT pa.paquete_nombre, pa.paquete_id\r\n" + 
				"FROM paquetes pa   \r\n" + 
				"WHERE \r\n" + 
				"1=1 and pa.paquete_estatus=1\r\n" + 
				"and pa.paquete_id NOT IN \r\n" + 
				"(SELECT copa.paquete_id \r\n" + 
				" FROM  paquetes pa, convenio_paquete copa \r\n" + 
				"		WHERE 1=1 \r\n" + 
				"		and copa.paquete_id=pa.paquete_id\r\n" + 
				"		and copa.convenio_id="+id+")").getResultList();
		
		
		
		return  re;
	}
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Object[]> findPaqueteConvenio(Long id) {
		// TODO Auto-generated method stub
		
		
		
		
		List<Object[]> re= em.createNativeQuery("SELECT pa.paquete_nombre,copa.copa_id \r\n" + 
				" FROM  paquetes pa, convenio_paquete copa \r\n" + 
				"		WHERE 1=1 \r\n" + 
				"		and copa.paquete_id=pa.paquete_id\r\n" + 
				"		and copa.convenio_id="+id).getResultList();
		
		
		
		return  re;
	}
}
