package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.bemedica.springboot.app.models.entity.ValorReferencia;
@Repository
public class ValorReferenciaDaoImpl implements IValorReferenciaDao {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<ValorReferencia> findAll() {
		// TODO Auto-generated method stub
		return em.createQuery ("from ValorReferencia").getResultList();
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public List<ValorReferencia> findAllById(Long id) {
		// TODO Auto-generated method stub
		return em.createQuery ("from ValorReferencia where EstudioId="+id).getResultList();
	}

	@Override
	@Transactional
	public void save(ValorReferencia valorReferencia) {
		// TODO Auto-generated method stub
		if(valorReferencia.getValorRefeId() !=null &&  valorReferencia.getValorRefeId()>0) {
			em.merge(valorReferencia);
			
		}
		else {
			em.persist(valorReferencia);
		}
	}
	

	@Override
	@Transactional(readOnly= true)
	public ValorReferencia findOne(Long id) {
		// TODO Auto-generated method stub
		return em.find(ValorReferencia.class,id);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		// TODO Auto-generated method stub
		em.remove(findOne(id));
	}
	
}
