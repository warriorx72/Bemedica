package com.bemedica.springboot.app.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bemedica.springboot.app.models.dao.IParticipacionVistaDao;

@Controller
public class ParticipacionesVistaController {

	@Autowired
	private IParticipacionVistaDao participacionDao;
	
	
	@RequestMapping(value="/lista_participaciones/{Id}",method=RequestMethod.GET)
	public String listar(Model model,@PathVariable(value="Id")Long Id) {
		model.addAttribute("id",Id);
		model.addAttribute("titulo","Participaciones de Médicos");
		model.addAttribute("participacion_vista",participacionDao.findAll());
		return "lista_participaciones";
	}
	
}
