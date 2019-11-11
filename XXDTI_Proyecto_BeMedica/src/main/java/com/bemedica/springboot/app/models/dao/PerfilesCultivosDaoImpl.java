package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.PerfilesCultivos;
import com.bemedica.springboot.app.models.entity.PerfilesCultivosQ;
@Repository
public class PerfilesCultivosDaoImpl implements IPerfilesCultivosDao {

	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<PerfilesCultivos> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from PerfilesEstudios").getResultList();
	}

	@Override
	@Transactional
	public void save(PerfilesCultivos perfilesCultivos) {
		// TODO Auto-generated method stub
		if(perfilesCultivos.getPeCuId()!=null &&  perfilesCultivos.getPeCuId()>0) {
			em.merge(perfilesCultivos);
			
		}
		else {
			em.persist(perfilesCultivos);
		}
	}

	@Override
	@Transactional(readOnly= true)
	public PerfilesCultivos findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(PerfilesCultivos.class,id);
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
	public List<PerfilesCultivosQ> findAllById(Long id) {
		// TODO Auto-generated method stub
		return em.createNativeQuery("SELECT\r\n" + 
				"pc.pecu_id,\r\n" + 
				"pc.perfil_id,\r\n" + 
				"e.estudio_nombre\r\n" + 
				"FROM\r\n" + 
				"perfiles_cultivos AS pc ,\r\n" + 
				"estudios AS e\r\n" + 
				"WHERE 1=1\r\n" + 
				"	and e.estudio_id = pc.cultivo_id\r\n" + 
				"	and pc.perfil_id="+id,PerfilesCultivosQ.class).getResultList();
	}

}
