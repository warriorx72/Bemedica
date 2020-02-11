package com.bemedica.springboot.app.service;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bemedica.springboot.app.models.entity.Role;
import com.bemedica.springboot.app.repository.UserRepository;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		com.bemedica.springboot.app.models.entity.User appUser = repository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Login Username Invalido."));
int status=Integer.parseInt(appUser.getUserStatus());
		if(status==0 || status!=1) {
	System.out.println("adios");
	System.out.println(status);
 throw new UsernameNotFoundException("Login Username Invalido.");

}
else {
	System.out.println("hola");
	System.out.println(status);
}
System.out.println(appUser.getUserStatus());
		Set grantList = new HashSet();
		for(Role role: appUser.getRoles()) {
			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getDescription());
            grantList.add(grantedAuthority);
		}
		
		UserDetails user = (UserDetails) new User(username, appUser.getPassword(),grantList);
		return user;
	}

}
