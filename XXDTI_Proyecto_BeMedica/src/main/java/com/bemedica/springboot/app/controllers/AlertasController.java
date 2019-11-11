package com.bemedica.springboot.app.controllers;

import java.util.Map;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bemedica.springboot.app.models.dao.IAlertasDao;
import com.bemedica.springboot.app.models.entity.Alertas;

@Controller
@SessionAttributes("alertas")
public class AlertasController {
	
	@Autowired
	private IAlertasDao alertasDao;

	@RequestMapping(value="/catalogos_alertas", method=RequestMethod.GET)
	private String listar(Model model) {
		model.addAttribute("titulo","Alertas");
		model.addAttribute("alertas",alertasDao.findAll());
		return "catalogos_alertas";
	}
	
	@RequestMapping(value="/form_alertas")
	public String crear(Map<String, Object> model) {
		
		Alertas alertas = new Alertas();
		model.put("alertas", alertas);
		model.put("titulo", "Form");
		
		return "form_alertas";
	}
	
	@RequestMapping(value="/form_alertas", method=RequestMethod.POST)
	public String guardar(Alertas alertas) {
		alertasDao.save(alertas);
		return "redirect:catalogos_alertas";	
	}
	
	
	@RequestMapping(value="/form_alertas/{AlertaId}")
	public String editar(@PathVariable(value="AlertaId") Long AlertaId, Map<String, Object> model) {
		
		Alertas alertas= null;	
		
		if(AlertaId > 0) {
			alertas = alertasDao.findOne(AlertaId);
		}else {
			return "redirect:catalogos_alertas";
		}
		model.put("alertas", alertas);
		model.put("titulo", "Editar Alerta");
		return "form_alertas";
	}
	
	@RequestMapping(value="/eliminaraler/{AlertaId}")
	public String eliminaraler(@PathVariable(value="AlertaId") Long AlertaId) {
		
		if(AlertaId > 0) {
			alertasDao.delete(AlertaId);
		}
		return "redirect:/catalogos_alertas";
	}
}
