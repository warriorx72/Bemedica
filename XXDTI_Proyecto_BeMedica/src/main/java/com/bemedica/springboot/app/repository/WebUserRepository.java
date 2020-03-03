package com.bemedica.springboot.app.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bemedica.springboot.app.models.entity.WebUser;



@Repository

public interface WebUserRepository extends CrudRepository<WebUser, Long>{

	

	public WebUser findBypaciente(int id);
	

}
