package com.bemedica.springboot.app.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.bemedica.springboot.app.dto.ChangePasswordForm;
import com.bemedica.springboot.app.models.entity.User;
import com.bemedica.springboot.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	@PersistenceContext
	private EntityManager em;
	
	@Autowired
	UserRepository repository;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public Iterable<User> getAllUsers() {
		return repository.findAll();
	}
	
	private boolean checkUsernameAvailable(User user) throws Exception {
		Optional<User> userFound = repository.findByUsername(user.getUsername());
		if(userFound.isPresent()) {
			throw new Exception("Nombre de Usuario no disponible");
		}
		return true;
	}
	
//	private boolean checkEmailAvailable(User user) throws Exception{
//		Optional<User> userFound = repository.findByEmail(user.getEmail());
//		if(userFound.isPresent()) {
//			throw new Exception("Correo Electronico ya esta utilizado por otro usuario");
//		}
//		return true;
//	}

	private boolean checkPasswordValid(User user) throws Exception {
		if(user.getConfirmPassword() == null || user.getConfirmPassword().isEmpty()) {
			throw new Exception("Confirmar Contraseña es obligatorio");
		}
		
		if( !user.getPassword().equalsIgnoreCase(user.getConfirmPassword())) {
			throw new Exception("Contraseña y Confirmacion no son iguales");
		}
		return true;
	}

	@Override
	public User createUser(User user) throws Exception {
		if(checkUsernameAvailable(user) && checkPasswordValid(user)) {
			String encodePassword = bCryptPasswordEncoder.encode(user.getPassword());
			user.setPassword(encodePassword);
			user = repository.save(user);
		}
		return user;
	}

	@Override
	public User getUserById(Long id) throws Exception {
		return repository.findById(id).orElseThrow(() -> new Exception("El usuario no existe."));
	}

	@Override
	public User updateUser(User fromUser) throws Exception {
		User toUser = getUserById(fromUser.getId());
		mapUser(fromUser, toUser);
		return repository.save(toUser);	
	}
	
	protected void mapUser(User from, User to) {
		to.setUsername(from.getUsername());
		//to.setFirstName(from.getFirstName());
		//to.setLastName(from.getLastName());
		//to.setEmail(from.getEmail());
		to.setEmpleado_id(from.getEmpleado_id());
		to.setRoles(from.getRoles());
		to.setPassword(from.getPassword());
		to.setUserStatus(from.getUserStatus());
	}

	@Override
	public void deleteUser(Long id) throws Exception {
		User user = getUserById(id);
		repository.delete(user);
	}

	@Override
	public User changePassword(ChangePasswordForm form) throws Exception {
		User user  = getUserById(form.getId());
		
		if( !isLoggedUserAdmin() && !user.getPassword().equals(form.getCurrentPassword())) {
			throw new Exception("Currente Password invalido.");
		}
		
		if( user.getPassword().equals(form.getNewPassword())) {
			throw new Exception("Nueva Contraseña debe ser diferente a la contraseña actual.");
		}
		
		if( !form.getNewPassword().equals(form.getConfirmPassword())) {
			throw new Exception("Nueva Contraseña y Confirmar Contraseña no coinciden.");
		}
		
		String encodePassword = bCryptPasswordEncoder.encode(form.getNewPassword());
		user.setPassword(encodePassword);
		return repository.save(user);
	}

	
	private boolean isLoggedUserAdmin() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails loggedUser = null;
		if(principal instanceof UserDetails) {
			loggedUser = (UserDetails) principal;
			
			loggedUser.getAuthorities().stream()
			.filter(x -> "ADMIN".equals(x.getAuthority() ))
			.findFirst().orElse(null);
		}
		return loggedUser != null ?true :false;
	}
                                                        
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Object> emp_suc(String rol,String username){
	return em.createNativeQuery("select empleado_vista.empleado_id,sucursal.sucursal_id, user.id \r\n" + 
			"from user,user_roles,role,empleado_vista,empleados_sucursal,sucursal \r\n" + 
			"where user.id=user_roles.user_id\r\n" + 
			"and user_roles.role_id=role.id and \r\n" + 
			"user.empleado_id=empleado_vista.empleado_id\r\n" + 
			"and user.empleado_id=empleados_sucursal.empleado_id\r\n" + 
			"and empleado_vista.empleado_id=empleados_sucursal.empleado_id\r\n" + 
			"and empleados_sucursal.sucursal_id=sucursal.sucursal_id\r\n" + 
			"and\r\n" + 
			"user.username=\r\n"+
			""+"'"+username+"'"+
			"and role.description=\r\n"+
			""+"'"+rol+"'").getResultList();
	
	}
	
}
