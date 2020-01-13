package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.dao.IPaquetesGabinetesDao;
import com.bemedica.springboot.app.models.entity.PaquetesCultivosQ;
import com.bemedica.springboot.app.models.entity.PaquetesGabinetes;
@Repository
public class PaquetesGabinetesDaoImpl implements IPaquetesGabinetesDao {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<PaquetesGabinetes> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from PaquetesGabinetes").getResultList();
	}

	@Override
	@Transactional
	public void save(PaquetesGabinetes paquetesGabinetes) {
		// TODO Auto-generated method stub
		if(paquetesGabinetes.getPaGaId() !=null &&  paquetesGabinetes.getPaGaId()>0) {
			em.merge(paquetesGabinetes);
			
		}
		else {
			em.persist(paquetesGabinetes);
		}
	}

	@Override
	@Transactional
	public PaquetesGabinetes findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(PaquetesGabinetes.class,id);
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
	public List<Object[]> findAllById(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("SELECT\r\n" + 
				"pg.paga_id,\r\n" + 
				"pg.paquete_id,\r\n" + 
				"e.estudio_nombre\r\n" + 
				"FROM\r\n" + 
				"paquetes_gabinetes AS pg ,\r\n" + 
				"estudios AS e\r\n" + 
				"WHERE 1=1\r\n" + 
				"	and e.estudio_id = pg.gabinete_id\r\n" + 
				"	and pg.paquete_id="+id).getResultList();
	}

}
