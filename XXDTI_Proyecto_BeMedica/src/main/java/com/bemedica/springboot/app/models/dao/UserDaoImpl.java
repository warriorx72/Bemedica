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
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	@Override
	public List<Object> lista() {
		
		return em.createNativeQuery("select user.*,empleado_vista.empleado_id_text,empleado_vista.persona_nombre,empleado_vista.persona_ap,\r\n" + 
				"empleado_vista.persona_am,empleado_vista.persona_email,empleado_vista.sucursal_nombre,empleado_vista.empleado_estatus\r\n" + 
				",user_roles.*,role.id as role_id_2, role.description,role.name from user,empleado_vista,user_roles,role\r\n" + 
				"where user.empleado_id=empleado_vista.empleado_id\r\n" + 
				"and empleado_vista.empleado_id=user.empleado_id\r\n" + 
				"and user.id=user_roles.user_id\r\n" + 
				"and user_roles.user_id=user.id\r\n" + 
				"and user_roles.role_id=role.id\r\n" + 
				"and role.id=user_roles.role_id\r\n" + 
				"order by user.id desc\r\n" + 
				";").getResultList();
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
