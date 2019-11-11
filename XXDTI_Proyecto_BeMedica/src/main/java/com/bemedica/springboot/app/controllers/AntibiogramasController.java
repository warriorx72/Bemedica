package com.bemedica.springboot.app.controllers;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;

import com.bemedica.springboot.app.models.dao.IAntiCatalogosDao;
import com.bemedica.springboot.app.models.dao.IAntibiogramasDao;
import com.bemedica.springboot.app.models.entity.AntiCatalogos;
import com.bemedica.springboot.app.models.entity.Antibiogramas;
import com.bemedica.springboot.app.models.entity.Perfiles;

@Controller
public class AntibiogramasController {
	
	@Autowired
	private IAntibiogramasDao antibiogramasDao;
	
	@Autowired
	private IAntiCatalogosDao antiCatalogosDao;
	

	@RequestMapping(value="/estudios_antibiogramas")
	public String crear(Map<String, Object> model,Model m) {
		//m.addAttribute("estudios", estudiosDao.findAll());
		//m.addAttribute("catalogos", catalogoDao.findAll());
		model.put("titulo","Guardar Antibiograma");
		Antibiogramas antibiogramas = new Antibiogramas();
		AntiCatalogos antiCatalogos = new AntiCatalogos();
		model.put("antibiogramas",antibiogramas);
		model.put("antiCatalogos",antiCatalogos);
		return "estudios_antibiogramas";
	}
	
	@RequestMapping(value="/estudios_antibiogramas", method=RequestMethod.POST)
	public String guardar (Map<String, Object> m,@Valid Antibiogramas antibiogramas,BindingResult result , Model model,SessionStatus status)
	{				
		if(result.hasErrors()) {
			return "estudios_antibiogramas";
		}
		antibiogramas.setAntiEstatus(true);
		antibiogramasDao.save(antibiogramas);
		if(antibiogramas.getAntiIdText()=="") {
			char buf[] = new char[3];
			antibiogramas.getAntiNombre().getChars(0,3,buf,0);
			String IdText = String.valueOf(buf);
			antibiogramas.setAntiIdText(IdText.toLowerCase()+(antibiogramas.getAntiId()+10000));
		}
		antibiogramasDao.save(antibiogramas);
		m.put("titulo","Guardar Antibiograma");
		AntiCatalogos antiCatalogos = new AntiCatalogos();
		antiCatalogos.setAntiId(antibiogramas.getAntiId());
		m.put("antiCatalogos",antiCatalogos);
		model.addAttribute("catalogos", antiCatalogosDao.findCatalogos(antibiogramas.getAntiId()));
		model.addAttribute("catalogoss", antiCatalogosDao.findAllById(antibiogramas.getAntiId()));
		System.out.print(antiCatalogos.getAntiId());
		return "estudios_antibiogramas";
	}
	
	@RequestMapping(value="/antibiogramas_catalogos", method=RequestMethod.POST)
	public String guardarAntiCatalogos (Map<String, Object> m,AntiCatalogos antiCatalogos,BindingResult result , Model model,SessionStatus status)
	{	
		
		Antibiogramas aux=null;
		System.out.print(antiCatalogos.getAntiId());
		aux=antibiogramasDao.findOne(antiCatalogos.getAntiId());
		if(result.hasErrors()) {
			return "estudios_perfiles";
		}
		antiCatalogos.setAntiId(aux.getAntiId());
		antiCatalogosDao.save(antiCatalogos);
		m.put("titulo","Guardar estudio");
		m.put("antiCatalogos",antiCatalogos);
		m.put("antibiogramas",aux);
		model.addAttribute("catalogoss", antiCatalogosDao.findAllById(antiCatalogos.getAntiId()));
		model.addAttribute("catalogos", antiCatalogosDao.findCatalogos(antiCatalogos.getAntiId()));
		return "estudios_antibiogramas";
	}
	@RequestMapping (value="/EliminarCat/{id}/{id_e}")
	public String eliminar(@PathVariable (value="id") Long id,@PathVariable (value="id_e") Long id_e,Model m,Map<String, Object> model,Perfiles perfil) {
		model.put("titulo","Guardar Antibiograma");
		if(id > 0) {
			Antibiogramas aux=null;
			aux=antibiogramasDao.findOne(id_e);
			AntiCatalogos antiCatalogos = new AntiCatalogos();
			antiCatalogos.setAntiId(aux.getAntiId());;
			model.put("antiCatalogos",antiCatalogos);
			model.put("antibiogramas",aux);
			antiCatalogosDao.delete(id);
			m.addAttribute("catalogos", antiCatalogosDao.findCatalogos(id_e));
			m.addAttribute("catalogoss", antiCatalogosDao.findAllById(id_e));
			return "estudios_antibiogramas";
		}

		return "redirect:estudios_antibiogramas";
	}
	
	@RequestMapping(value="/estudios_antibiogramas/{id}")
	public String editar(@PathVariable(value="id") Long id, Map<String, Object> model,Model m) {
		Antibiogramas antibiogramas=null;
		AntiCatalogos antiCatalogos = new AntiCatalogos();
		if(id>0) {
			System.out.print("i´m here"+id);
			antibiogramas=antibiogramasDao.findOne(id);
		}
		else {
			return "redirect:estudios_listar/";
		}
		model.put("antibiogramas",antibiogramas);
		antiCatalogos.setAntiId(id);
		model.put("antiCatalogos",antiCatalogos);
		model.put("titulo","Guardar Antibiograma");
		m.addAttribute("catalogos", antiCatalogosDao.findCatalogos(id));
		m.addAttribute("catalogoss", antiCatalogosDao.findAllById(id));
		return "estudios_antibiogramas";		
	}
	
}
