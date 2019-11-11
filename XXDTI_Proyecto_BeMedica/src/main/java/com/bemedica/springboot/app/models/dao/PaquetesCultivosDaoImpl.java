package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.PaquetesCultivos;
import com.bemedica.springboot.app.models.entity.PaquetesCultivosQ;
@Repository
public class PaquetesCultivosDaoImpl implements IPaquetesCultivosDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<PaquetesCultivos> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from PaquetesCultivos").getResultList();
	}

	@Override
	@Transactional
	public void save(PaquetesCultivos paquetesCultivos) {
		// TODO Auto-generated method stub
		if(paquetesCultivos.getPaCuId() !=null &&  paquetesCultivos.getPaCuId()>0) {
			em.merge(paquetesCultivos);
			
		}
		else {
			em.persist(paquetesCultivos);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public PaquetesCultivos findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(PaquetesCultivos.class,id);
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
	public List<PaquetesCultivosQ> findAllById(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("SELECT\r\n" + 
				"pc.pacu_id,\r\n" + 
				"pc.paquete_id,\r\n" + 
				"e.estudio_nombre\r\n" + 
				"FROM\r\n" + 
				"paquetes_cultivos AS pc ,\r\n" + 
				"estudios AS e\r\n" + 
				"WHERE 1=1\r\n" + 
				"	and e.estudio_id = pc.cultivo_id\r\n" + 
				"	and pc.paquete_id="+id,PaquetesCultivosQ.class).getResultList();
	}

}
