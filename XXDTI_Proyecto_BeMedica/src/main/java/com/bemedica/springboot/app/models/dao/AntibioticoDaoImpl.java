package com.bemedica.springboot.app.models.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.Antibiotico;


@Repository
public class AntibioticoDaoImpl implements IAntibioticoDao {
	
	
	@PersistenceContext
	private EntityManager em;
	@Transactional(readOnly =true)
	@SuppressWarnings("unchecked")
	
	//-----------------------------metodoList
	@Override
	public List<Antibiotico> findAll() {
		return em.createQuery("From Antibiotico").getResultList();
	}//end_List

	//-----------------------------metodofindOne
	@Override
	@Transactional(readOnly =true)
	public Antibiotico findOne(Long CatalogoId) {
		// TODO Auto-generated method stub
		return em.find(Antibiotico.class, CatalogoId);
	}//end findOne

	

	//-----------------------------metodoDelete
    @Override
	@Transactional
	public void delete(Long CatalogoId) {
	em.remove(findOne(CatalogoId));
		
	}//end_delete

	//-----------------------------metodoSave
	@Override
	@Transactional
	public void save(Antibiotico antibiotico) {
		if(antibiotico.getCatalogoId() != null && antibiotico.getCatalogoId() >0) {
			em.merge(antibiotico);
		}else {
			em.persist(antibiotico);	
		}
	}//end_Save

}//end_Class
