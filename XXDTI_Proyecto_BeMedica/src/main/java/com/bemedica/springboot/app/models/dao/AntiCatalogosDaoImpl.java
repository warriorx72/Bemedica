package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.AntiCatalogos;
import com.bemedica.springboot.app.models.entity.AntiCatalogosQ;
@Repository
public class AntiCatalogosDaoImpl implements IAntiCatalogosDao {
	
	
	@PersistenceContext
	private EntityManager em;
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<AntiCatalogos> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from AntiCatalogos").getResultList();
	}

	@Override
	@Transactional
	public void save(AntiCatalogos antiCatalogos) {
		// TODO Auto-generated method stub
		if(antiCatalogos.getAntiCatalogoId() !=null &&  antiCatalogos.getAntiCatalogoId()>0) {
			em.merge(antiCatalogos);
			
		}
		else {
			em.persist(antiCatalogos);
		}

	}

	@Override
	@Transactional
	public AntiCatalogos findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(AntiCatalogos.class,id);
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
	public List<AntiCatalogosQ> findAllById(Long id) {
		Query query = em.createNativeQuery("SELECT ac.anti_catalogo_id,ac.anti_id,c.nombre\r\n" + 
				"FROM catalogo c,anti_catalogos ac\r\n" + 
				"WHERE 1=1\r\n" + 
				"			AND c.catalogo_id=ac.catalogo_id\r\n" + 
				"			AND ac.anti_id="+id,AntiCatalogosQ.class);
		
		List<AntiCatalogosQ> result = query.getResultList();
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AntiCatalogos> findCatalogos(Long id) {
		// TODO Auto-generated method stub
		return em.createQuery (
				"FROM\r\n" + 
				"Catalogo AS ca\r\n" + 
				"WHERE\r\n" + 
				"1 = 1 AND\r\n" + 
				"ca.NombreCat = 'antibiotico' AND\r\n" + 
				"ca.CatalogoId NOT IN (SELECT\r\n" + 
				"c.CatalogoId\r\n" + 
				"FROM\r\n" + 
				"Catalogo AS c ,\r\n" + 
				"AntiCatalogos AS ac\r\n" + 
				"WHERE\r\n" + 
				"1 = 1 AND\r\n" + 
				"c.CatalogoId = ac.CatalogoId AND\r\n" + 
				"ac.AntiId ="+id+")").getResultList();
	}

}
