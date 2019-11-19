package com.bemedica.springboot.app.models.dao;

import java.util.List;
import com.bemedica.springboot.app.models.entity.ConvenioEstudio;
import com.bemedica.springboot.app.models.entity.ConvenioEstudioVista;

public interface IConvenioEstudio {
	
	public List<ConvenioEstudio> findAll();
	public List<ConvenioEstudioVista> cev (Long id );
	
	public void save (ConvenioEstudio convenioestudio);
	
	public ConvenioEstudio findOne (Long id );
	
	public void delete (Long id);


}
