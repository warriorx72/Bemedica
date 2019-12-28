package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bemedica.springboot.app.models.entity.EmpleadoVista;
import com.bemedica.springboot.app.models.entity.User;
import com.bemedica.springboot.app.repository.UserRepository;

@Repository
public class UserDaoImpl implements IUserDao {

	@Autowired
	EntityManager em;
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<EmpleadoVista> ev() {
		
		return em.createQuery("from EmpleadoVista").getResultList();
	}

	@Override
	@Transactional
	public void save(User user) {
		if(user.getId() != null && user.getId() > 0) {
			String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encodePassword);
			user = repository.save(user);
			em.merge(user);
			
		}else {
			String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encodePassword);
			user = repository.save(user);
			em.persist(user);
		}
	}

}
