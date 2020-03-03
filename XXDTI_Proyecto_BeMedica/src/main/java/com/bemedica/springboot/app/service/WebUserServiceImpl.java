package com.bemedica.springboot.app.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bemedica.springboot.app.models.entity.WebUser;
import com.bemedica.springboot.app.repository.WebUserRepository;

@Service

public class WebUserServiceImpl implements WebUserService {
	
	@Autowired
	WebUserRepository repository;

	@Override
	public WebUser findBypaciente(int id) {
		// TODO Auto-generated method stub
		return (WebUser) repository.findBypaciente(id);
	}
	
	

}
