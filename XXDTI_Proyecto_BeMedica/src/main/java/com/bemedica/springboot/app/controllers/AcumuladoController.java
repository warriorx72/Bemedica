package com.bemedica.springboot.app.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.bemedica.springboot.app.models.dao.IAcumuladoDao;
import com.bemedica.springboot.app.models.dao.IPagosVistaDao;


@Controller
@SessionAttributes("acumulado")
public class AcumuladoController {
	
	@Autowired
	private IAcumuladoDao acumuladoDao;
	
	@Autowired
	private IPagosVistaDao pagosDao;
	
	
	
	@RequestMapping(value="/herramientas_participaciones",method=RequestMethod.GET)
	public String listar(Model model) {
		model.addAttribute("titulo","Participaciones de Médicos");
		model.addAttribute("acumulado_vista",acumuladoDao.findAll());
		return "herramientas_participaciones";
	}
	
	@RequestMapping(value="/lista_pagos_medico/{Id}",method=RequestMethod.GET)
	public String listar2(Model model,@PathVariable(value="Id")Long Id) {
		model.addAttribute("id",Id);
		model.addAttribute("titulo","Listado de Pagos");
		model.addAttribute("pagos_medico_vista",pagosDao.findAll());
		return "lista_pagos_medico";
	}
	
	
	
}
