package com.bemedica.springboot.app.service;

import java.util.Optional;

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
		to.setFirstName(from.getFirstName());
		to.setLastName(from.getLastName());
		to.setEmail(from.getEmail());
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
	
}