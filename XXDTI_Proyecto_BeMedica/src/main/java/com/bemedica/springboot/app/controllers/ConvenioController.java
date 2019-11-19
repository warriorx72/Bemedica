package com.bemedica.springboot.app.controllers;

import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.bemedica.springboot.app.models.dao.IConvenio;
import com.bemedica.springboot.app.models.dao.IConvenioEstudio;
import com.bemedica.springboot.app.models.dao.IEmpresa;
import com.bemedica.springboot.app.models.dao.IEstudio;
import com.bemedica.springboot.app.models.entity.Convenio;
import com.bemedica.springboot.app.models.entity.ConvenioEstudio;

@Controller
public class ConvenioController {
	
	@Autowired
	@Qualifier("ConvenioDaoJPA")
	private IConvenio ConvenioDao;
	
	@Autowired
	@Qualifier("EmpresaDaoJPA")
	private IEmpresa EmpresaDao;
	
	@Autowired
	@Qualifier("ConvenioEstudioDaoJPA")
	private IConvenioEstudio ConvenioEstudioDao;
	
	@Autowired
	@Qualifier("EstudioDaoJPA")
	private IEstudio EstudioDao;
	
	
	
	@RequestMapping (value="/precios_convenios", method=RequestMethod.GET)
	public String listar (Model model) {
		model.addAttribute("titulo","Convenios");
		model.addAttribute("vista", ConvenioDao.All());
		return "precios_convenios";
	}
	
	@RequestMapping(value="/form_convenio")
	public String crear (Map<String, Object> model , Model m)
	{
		Convenio c = new Convenio();
		ConvenioEstudio ce = new ConvenioEstudio();
		m.addAttribute("vistas", EmpresaDao.findAll());
		
		// aqui se mandan los estudios disponibles
		m.addAttribute("vistasEstudio", EstudioDao.select(c.getConvenioId()));
		
		
		m.addAttribute("vista_convenio_estudio", ConvenioEstudioDao.cev(c.getConvenioId()));
		model.put("convenio", c);
		model.put("ce", ce);
		model.put("titulo","Nuevo Convenio");
		
		m.addAttribute("disableSecondButton", true);
		
		
		m.addAttribute("vista_convenio_estudio", ConvenioEstudioDao.cev(c.getConvenioId()));
		// aqui se mandan los estudios disponibles

		return "form_convenio";
	
	}
	
	
	@RequestMapping(value= "/form_convenio_convenio", method=RequestMethod.POST)
	public String guardar_convenio (@Valid Convenio convenio,BindingResult result, Model model, Map<String, Object> m ){
		
		/*
		if ( result.hasErrors()){
			model.addAttribute("titulo","Nuevo Convenio");
			model.addAttribute("vistas", EmpresaDao.findAll());
			
			return "form_convenio";
		}*/
		
		model.addAttribute("vistas", EmpresaDao.findAll());
		
		convenio.setConvenioIdText("null");
		ConvenioDao.save(convenio);
		
		convenio.setConvenioIdText("CONV"+convenio.getConvenioNombre().charAt(0)+convenio.getConvenioNombre().charAt(1)+convenio.getConvenioNombre().charAt(2)+""+(convenio.getConvenioId()+10000));
		
		ConvenioDao.save(convenio);
		
		
		model.addAttribute("mensaje", "Convenio guardado correctamente")
        .addAttribute("clase", "success");
		ConvenioEstudio ce = new ConvenioEstudio();
		ce.setConvenioId(convenio.getConvenioId());
		m.put("ce", ce);
		model.addAttribute("vistasEstudio", EstudioDao.findAll());
		model.addAttribute("vista_convenio_estudio", ConvenioEstudioDao.cev(convenio.getConvenioId()));
		model.addAttribute("disableSecondButton", false);
		model.addAttribute("vistasEstudio", EstudioDao.select(ce.getConvenioId()));
		
		
			return"form_convenio";
	}
	
	
	
	@RequestMapping(value= "/form_convenio_estudios", method=RequestMethod.POST)
	public String guardar_convevioestudio (@Valid ConvenioEstudio ce, BindingResult result, Convenio convenio ,  Model model, Map<String, Object> m){
		
		
		Convenio aux=null;
		/*if ( result.hasErrors()){
			aux=ConvenioDao.findOne(ce.getConvenioId());
			model.addAttribute("vistas", EmpresaDao.findAll());
			m.put("ce", ce);
			m.put("convenio", aux);
			model.addAttribute("vista_convenio_estudio", ConvenioEstudioDao.cev(aux.getConvenioId()));
			model.addAttribute("vistasEstudio", EstudioDao.select(ce.getConvenioId()));
			return "form_convenio";
		}*/
		
		
		
		aux=ConvenioDao.findOne(ce.getConvenioId());
		model.addAttribute("vistasEstudio", EstudioDao.findAll());
		model.addAttribute("vistas", EmpresaDao.findAll());
		
		ConvenioEstudioDao.save(ce);
		m.put("ce", ce);
		m.put("convenio", aux);
		model.addAttribute("vista_convenio_estudio", ConvenioEstudioDao.cev(aux.getConvenioId()));
		model.addAttribute("disableSecondButton", false);
		
		
		model.addAttribute("mensaje2", "Estudio agregado correctamente")
        .addAttribute("clase2", "success");
		
		if (EstudioDao.select(ce.getConvenioId())== null  ||  EstudioDao.select(ce.getConvenioId()).isEmpty()) {
			
			model.addAttribute("disableSecondButton", true);
			
		}
		model.addAttribute("vistasEstudio", EstudioDao.select(ce.getConvenioId()));
		return"form_convenio";
	}
	
	@RequestMapping(value= "/form_convenio_editar/{id}")
	public String editar(@PathVariable (value="id") Long id, Map<String, Object> model, Model m ) {
		Convenio  c = null;
		ConvenioEstudio ce = new ConvenioEstudio();
		if (id>0) {
			c=ConvenioDao.findOne(id);
			
		
		}
		
		else {
			return "redirect:/precios_convenios";
		}
		m.addAttribute("vistas", EmpresaDao.findAll());
		m.addAttribute("vista_convenio_estudio", ConvenioEstudioDao.cev(c.getConvenioId()));
		model.put("titulo", "Editar Convenio");
		model.put("convenio", c);
		ce.setConvenioId(id);
		model.put("ce", ce);
		m.addAttribute("vistasEstudio", EstudioDao.select(ce.getConvenioId()));
		
		if (EstudioDao.select(ce.getConvenioId())== null  ||  EstudioDao.select(ce.getConvenioId()).isEmpty()) {
			
			m.addAttribute("disableSecondButton", true);
			
		}
		
		return "form_convenio";
	}
	
	@RequestMapping (value="/eliminar_convenio/{id}")
	public String eliminar(@PathVariable (value="id") Long id) {
		if (id > 0 )
		{
			ConvenioDao.delete(id);
		}
		
		return "redirect:/precios_convenios";
	}
	
	@RequestMapping (value="/eliminar_ce/{id_ce}/{id_c}")
	public String eliminar_ce(@PathVariable (value="id_ce") Long id_ce,@PathVariable (value="id_c") Long id_c , Map<String, Object> model , Model m,Convenio convenio) {
		
		
		if (id_ce > 0 )
		{
			
			Convenio aux=null;
	
			
			aux=ConvenioDao.findOne(id_c);
			m.addAttribute("vistasEstudio", EstudioDao.findAll());
			m.addAttribute("vistas", EmpresaDao.findAll());
			


			
			ConvenioEstudio ce = new ConvenioEstudio();
			ce.setConvenioId(aux.getConvenioId());
			model.put("ce", ce);
			
			
			model.put("convenio", aux);
			
			
			ConvenioEstudioDao.delete(id_ce);
			


	
			m.addAttribute("vista_convenio_estudio", ConvenioEstudioDao.cev(aux.getConvenioId()));
			m.addAttribute("vistasEstudio", EstudioDao.select(ce.getConvenioId()));
			
			m.addAttribute("mensaje2", "Estudio eliminado correctamente")
	        .addAttribute("clase2", "info");
			return "form_convenio";
			
		}
		
		return "redirect:/precios_convenios";
	}
	
	
	
}
