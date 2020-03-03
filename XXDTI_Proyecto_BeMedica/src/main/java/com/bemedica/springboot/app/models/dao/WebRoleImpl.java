package com.bemedica.springboot.app.models.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.WebRole;

@Repository

public class WebRoleImpl implements IWebRoleDao {
	
	@PersistenceContext
	private EntityManager em;

	@Override
	@Transactional
	public WebRole findOne(long id) {
		// TODO Auto-generated method stub
		return em.find(WebRole.class, id);
	}

}
