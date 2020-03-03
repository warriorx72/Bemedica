package com.bemedica.springboot.app.models.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.WebUser;



@Repository

public class WebUserDaoImpl implements  IWebUserDao {
	
	
	@PersistenceContext
	EntityManager em;
	
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Transactional
	@Override
	
	public void save(WebUser user) {
		if(user.getUser_id() != null && user.getUser_id() > 0) {
			String encodePassword = bCryptPasswordEncoder.encode(user.getUser_password());
			user.setUserPassword(encodePassword);
			em.merge(user);
			
		}else {
			String encodePassword = bCryptPasswordEncoder.encode(user.getUser_password());

			user.setUserPassword(encodePassword);
			em.persist(user);
		}
}
	
	
	
	
	
	
	
	
	
	
	@Transactional
	@Override	
	public String Exist(Long id) {
		
		String val =em.createNativeQuery("SELECT if ((SELECT COUNT(*) FROM web_user u INNER JOIN paciente p  ON u.paciente_id= p.paciente_id where  u.Tipo='empresa' AND u.paciente_id="+id+")>0, 1 , 0);").getSingleResult().toString();
		return val;
	}
	
	
	
	
	
	
	
	
	

	

}
