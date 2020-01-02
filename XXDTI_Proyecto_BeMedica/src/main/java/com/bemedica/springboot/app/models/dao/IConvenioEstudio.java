package com.bemedica.springboot.app.models.dao;

import java.util.List;
import com.bemedica.springboot.app.models.entity.ConvenioEstudio;
import com.bemedica.springboot.app.models.entity.ConvenioEstudioVista;

public interface IConvenioEstudio {
	
	public List<ConvenioEstudio> findAll();
	public List<ConvenioEstudioVista> cev (Long id);
	public List<Object> findExa(String id);
	public List<Object> findCul(String id);
	public List<Object> findGab(String id);
	public List<Object> findPaq(String id);
	public List<Object> findPer(String id);
	public void save (ConvenioEstudio convenioestudio);
	
	public ConvenioEstudio findOne (Long id );
	
	public void delete (Long id);
}
