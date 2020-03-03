package com.bemedica.springboot.app.models.dao;

import com.bemedica.springboot.app.models.entity.WebRole;

public interface IWebRoleDao {

	public WebRole findOne(long id);

}
