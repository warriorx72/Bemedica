package com.bemedica.springboot.app.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;


import com.bemedica.springboot.app.models.dao.ICajaVistaDao;


@SessionAttributes("caja_vista")
public class CajaVistaController {
	
	@Autowired
	private ICajaVistaDao cajaVistaDao;

@RequestMapping(value="/herramientas_corte", method=RequestMethod.GET)
	
     public String listar(Model model) {
		model.addAttribute("titulo1","Formulario catalogo");
		model.addAttribute("vistas", cajaVistaDao.findAll());
		
		
		return "herramientas_corte";
	   }
}